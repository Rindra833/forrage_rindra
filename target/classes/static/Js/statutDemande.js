document.addEventListener("DOMContentLoaded", function() {
    const selectStatuts = document.getElementById("statuts");
    
    if (selectStatuts) {
        selectStatuts.addEventListener("change", function() {
            const reference = document.getElementById("referenceDemande").value.trim();
            var form = document.getElementById("form");
            const idStatut = this.value;

            if (reference !== "" && idStatut !== "0") {
                fetch("/statut-demande/rechercher/" + reference + "/" + idStatut)
                    .then(response => {
                        if (!response.ok) throw new Error("StatutDemande introuvable");
                        return response.json();
                    })
                    .then(statutDemande => {
                        form.action = "/statut-demande/update/" + statutDemande.idStatutDemande;
                        document.getElementById("date").value = "";
                        document.getElementById("observation").value = "";

                        if (statutDemande.date && statutDemande.date !== null) {
                            document.getElementById("date").value = statutDemande.date;
                        }
                        
                        if (statutDemande.observation && statutDemande.observation !== null) {
                            document.getElementById("observation").value = statutDemande.observation;
                        }
                    })
                    .catch(err => {
                        console.error("Erreur lors de la récupération du statutDemande :", err);
                        document.getElementById("date").value = "";
                        document.getElementById("observation").value = "";
                    });
            } else {
                document.getElementById("date").value = "";
                document.getElementById("observation").value = "";
            }
        });
    }
});
