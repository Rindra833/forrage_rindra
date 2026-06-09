package com.forrage.controller;

import com.forrage.service.*;
import com.forrage.modele.*;
import com.forrage.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DevisController {
    @Autowired
    private DevisService devisService;

    @Autowired
    private TypeDevisRepository typeDevisRepository;

    @GetMapping("/devis/form")
    public String afficherFormulaire(Model model){
        List<TypeDevis> typesDevis = typeDevisRepository.findAll();
        model.addAttribute("devis", new Devis());
        model.addAttribute("typesDevis", typesDevis);
        
        return "devis/form";
    }

    @GetMapping("/devis/liste")
    public String afficherListeDevis(Model model) {
        List<Devis> devis = devisService.getAll();
        model.addAttribute("devis", devis);
        return "devis/listedevis";
    }

    @PostMapping("/devis/add")
    public String ajouterDevis(Devis devis, RedirectAttributes redirectAttributes) {
        try {
            /*Verification des champs s'ils sont vides*/
            if(devis.getReference() == null || devis.getReference().isEmpty()){
                throw new RuntimeException("La référence est obligatoire.");
            }

            if(devis.getDemande() == null || devis.getDemande().getReference() == null || devis.getDemande().getReference().isEmpty()){
                throw new RuntimeException("La demande est obligatoire.");
            }

            if(devis.getDate() == null){
                throw new RuntimeException("La date est obligatoire.");
            }

            if(devis.getTypeDevis() == null || devis.getTypeDevis().getIdType() == 0){
                throw new RuntimeException("Le type de devis est obligatoire.");
            }

            devisService.save(devis);

            redirectAttributes.addFlashAttribute("success", "Devis ajouté avec succès !");
            return "redirect:/devis/liste";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erreur", e.getMessage());
            return "redirect:/devis/form";
        }
    }
}
