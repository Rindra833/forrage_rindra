document.getElementById("referenceDemande").addEventListener("blur", function () {
    const reference = this.value.trim();
    
    if (reference === "") {
        document.getElementById("resultatDemande").innerHTML = "";
        return;
    }

    fetch("/demandes/rechercher/" + encodeURIComponent(reference))
        .then(response => {
            if (!response.ok) throw new Error("Non trouvé");
            return response.json();
        })
        .then(demande => {
            if (demande) {
                document.getElementById("resultatDemande").innerHTML = `
                    <div class="alert alert-success p-2">
                        <strong>Reference:</strong> — ${demande.reference}
                        <br>
                        <strong>Client:</strong> — ${demande.demandeur}
                        <br>
                        <strong>Date:</strong> — ${demande.date}
                        <br>
                        <strong>Lieu:</strong> — ${demande.lieu}
                    </div>
                `;

                fetch("/statut-demande/rechercher/statuts/" + reference)
                    .then(response => {
                        if (!response.ok) throw new Error("Erreur récupération statuts");
                        return response.json();
                    })
                    .then(statuts => {
                        const selectStatuts = document.getElementById("statuts");
                        
                        selectStatuts.innerHTML = '<option value="0">-- Sélectionnez une statut --</option>';
                        
                        
                        statuts.forEach(statut => {
                            const option = document.createElement("option");
                            option.value = statut.idStatut;
                            option.textContent = statut.libelle;
                            selectStatuts.appendChild(option);
                        });
                    })
                    .catch(err => console.error(err));

            } else {
                document.getElementById("referenceDemande").value = "";
                document.getElementById("resultatDemande").innerHTML = `
                    <div class="alert alert-danger p-2">
                        La demande <strong>${reference}</strong> n'existe pas.
                    </div>
                `;
            }
        })
        .catch(() => {
            document.getElementById("resultatDemande").innerHTML = `
                <div class="alert alert-danger p-2">
                    La demande <strong>${reference}</strong> n'existe pas.
                </div>
            `;
        });
});
