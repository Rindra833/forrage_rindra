package com.forrage.controller;

import com.forrage.service.*;
import com.forrage.modele.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StatutDemandeController {
    @Autowired
    private StatutDemandeService statutDemandeService;

    @Autowired
    private StatutService statutService;

    @Autowired
    private DemandeService demandeService;

    @GetMapping("/statut-demande/ajout")
    public String formulaireAjout(Model model) {
        model.addAttribute("statuts", statutService.getAllStatuts());
        model.addAttribute("statutDemande", new StatutDemande());

        return "statut_demande/ajout";
    }

    @GetMapping("/statut-demande/modif")
    public String formulaireModification(Model model) {
        model.addAttribute("statuts", statutService.getAllStatuts());
        model.addAttribute("statutDemande", new StatutDemande());

        return "statut_demande/modif";
    }

    @GetMapping("/statut-demande/rechercher/statuts/{reference}")
    @ResponseBody
    public List<Map<String, Object>> getStatutsFiltres(@PathVariable("reference") String reference) {
        List<StatutDemande> statutDemandes = statutDemandeService.getStatutDemandeByDemandeReference(reference);

        List<Map<String, Object>> statuts = new ArrayList<>();
        for (StatutDemande statutDemande : statutDemandes) {
            Map<String, Object> map = new HashMap<>();
            map.put("idStatut", statutDemande.getStatut().getIdStatut());
            map.put("libelle", statutDemande.getStatut().getLibelle());
            statuts.add(map);
        }

        return statuts;
    }

    @GetMapping("/statut-demande/rechercher/{reference}/{idStatut}")
    @ResponseBody
    public Map<String, Object> getStatutFiltre(@PathVariable("reference") String reference, @PathVariable("idStatut") int idStatut) {
        StatutDemande statutDemande = statutDemandeService.getStatutDemandeByDemandeReferenceAndStatutId(reference, idStatut);

        Map<String, Object> map = new HashMap<>();
        map.put("idStatutDemande", statutDemande.getIdStatutDemande());
        map.put("date", statutDemande.getDate());
        map.put("observation", statutDemande.getObservation());

        return map;
    }

    @PostMapping("/statut-demande/add")
    public String ajouterStatutDemande(StatutDemande statutDemande, RedirectAttributes redirectAttributes) {
        try {
            if (statutDemande.getDemande()==null || statutDemande.getDemande().getReference()==null || statutDemande.getDemande().getReference().isEmpty()) {
                throw new RuntimeException("La demande est obligatoire.");
            }

            if (statutDemande.getDate()==null) {
                LocalDateTime now=LocalDateTime.now();
                statutDemande.setDate(now);
            }

            if (statutDemande.getStatut()==null || statutDemande.getStatut().getIdStatut()==0) {
                throw new RuntimeException("Le statut est obligatoire.");
            }

            Demande demande = demandeService.getByReference(statutDemande.getDemande().getReference());
            
            statutDemande.setDemande(demande);

            Statut statut = statutService.getStatutById(statutDemande.getStatut().getIdStatut());

            statutDemande.setStatut(statut);

            statutDemandeService.save(statutDemande);

            redirectAttributes.addFlashAttribute("success", "Statut de demande ajouté avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erreur", "Erreur lors de l'ajout du statut de demande : " + e.getMessage());
        }
        return "redirect:/statut-demande/ajout";
    }

    @PostMapping("/statut-demande/update/{idStatutDemande}")
    public String modifierStatutDemande(@PathVariable("idStatutDemande") int idStatutDemande, StatutDemande statutDemande, RedirectAttributes redirectAttributes) {
        try {
            statutDemande.setIdStatutDemande(idStatutDemande);

            if (statutDemande.getDemande()==null || statutDemande.getDemande().getReference()==null || statutDemande.getDemande().getReference().isEmpty()) {
                throw new RuntimeException("La demande est obligatoire.");
            }

            if (statutDemande.getDate() == null) {
                LocalDateTime now=LocalDateTime.now();
                statutDemande.setDate(now);
            }

            Demande demande = demandeService.getByReference(statutDemande.getDemande().getReference());
            
            statutDemande.setDemande(demande);

            Statut statut= statutService.getStatutById(statutDemande.getStatut().getIdStatut());

            statutDemande.setStatut(statut);
            
            statutDemandeService.update(statutDemande);

            redirectAttributes.addFlashAttribute("success", "Statut de demande modifié avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erreur", "Erreur lors de la modification du statut de demande : " + e.getMessage());
        }
        return "redirect:/statut-demande/modif";
    }
}
