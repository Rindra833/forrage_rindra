package com.forrage.controller;

import com.forrage.service.*;
import com.forrage.modele.*;
import com.forrage.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Controller
public class DemandeController {
    @Autowired
    private DemandeService demandeService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CommuneRepository communeRepository;

    @Autowired
    private ParametreService parametreService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/demandes/form")
    public String afficherFormulaire(Model model){
        List<Client> clients = clientRepository.findAll(Sort.by(Sort.Direction.ASC,"nom"));
        List<Commune> communes = communeRepository.findAll(Sort.by(Sort.Direction.ASC,"libelle"));

        model.addAttribute("demande", new Demande());
        model.addAttribute("clients", clients);
        model.addAttribute("communes", communes);

        return "demande/form";
    }

    @GetMapping("/demandes/liste")
    public String afficherListeDemandes(Model model) {
        List<Demande> demandes = demandeService.getAll();
        model.addAttribute("demandes", demandes);

        return "demande/listedemande";
    }

    @GetMapping("/demandes/{idDemande}/alertes/liste")
    public String afficherAlertesDemande(@PathVariable("idDemande") int idDemande, Model model) {
        try {
            Demande demande = demandeService.getById(idDemande);
            if (demande == null) {
                model.addAttribute("erreur", "Demande non trouvée.");
                return "redirect:/demandes/liste";
            }

            List<Parametre> alertes = parametreService.getAllDemandeAlerte(demande);
            model.addAttribute("alertes", alertes);

            return "demande/listealerte";
        } catch (Exception e) {
            model.addAttribute("erreur", "Une erreur est survenue : " + e.getMessage());
            return "demande/listealerte";
        }
    }

    @PostMapping("/demandes/add")
    public String ajouterDemande(Demande demande, RedirectAttributes redirectAttributes) {
        try {
            /*Verification des champs s'ils sont vides*/
            if(demande.getReference() == null || demande.getReference().isEmpty()){
                throw new RuntimeException("La référence est obligatoire.");
            }

            if(demande.getDate() == null){
                throw new RuntimeException("La date est obligatoire.");
            }

            if (demande.getDemandeur() != null && demande.getDemandeur().getIdClient() == 0) {
                throw new RuntimeException("Vous devez sélectionner un client.");
            }

            if(demande.getLieu() == null || demande.getLieu().isEmpty()){
                throw new RuntimeException("Le lieu est obligatoire.");
            }

            if (demande.getCommune() != null && demande.getCommune().getIdCommune() == 0) {
                throw new RuntimeException("Vous devez sélectionner une commune.");
            }

            
            demandeService.save(demande);
            
            redirectAttributes.addFlashAttribute("success", "La demande a été ajoutée avec succès !");
            return "redirect:/demandes/liste";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erreur", e.getMessage());
            
            return "redirect:/demandes/form";
        }
    }

    @GetMapping("/demandes/rechercher/{reference}")
    @ResponseBody
    public Map<String, Object> rechercherDemande(@PathVariable("reference") String reference) {
        Demande demande = demandeService.getByReference(reference);

        if(demande == null) return null;

        Map<String, Object> response = new HashMap<>();
        response.put("reference", demande.getReference());
        response.put("date", demande.getDate());
        response.put("demandeur", demande.getDemandeur().getNom());
        response.put("lieu", demande.getLieu());

        return response;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/demandes/alertes/{reference}")
    @ResponseBody
    public List<Map<String, Object>> afficherAlertesDemandeApi(@PathVariable("reference") String reference) {
        Demande demande = demandeService.getByReference(reference);
        if (demande == null) {
            return new ArrayList<>();
        }
        
        List<Parametre> parametres = parametreService.getAllDemandeAlerte(demande);
        
        List<Map<String, Object>> response = new ArrayList<>();
        for(Parametre param : parametres) {
            Map<String, Object> map = new HashMap<>();
            
            if(param.getStatut1() != null) {
                Map<String, Object> statut1Map = new HashMap<>();
                statut1Map.put("idStatut", param.getStatut1().getIdStatut());
                statut1Map.put("libelle", param.getStatut1().getLibelle());
                map.put("statut1", statut1Map);
            }
            
            if(param.getStatut2() != null) {
                Map<String, Object> statut2Map = new HashMap<>();
                statut2Map.put("idStatut", param.getStatut2().getIdStatut());
                statut2Map.put("libelle", param.getStatut2().getLibelle());
                map.put("statut2", statut2Map);
            }
            
            map.put("durer", param.getDurer());
            map.put("alerte", param.getAlerte());
            
            response.add(map);
        }
        
        return response;
    }
}
