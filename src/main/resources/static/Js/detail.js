let index = 0;

document.getElementById("btnAjouterLigne").addEventListener("click", function () {
    const container = document.getElementById("lignesDetails");

    const ligne = document.createElement("div");
    ligne.classList.add("row", "g-2", "align-items-center", "mt-2");
    ligne.setAttribute("id", "ligne_" + index);

    ligne.innerHTML = `
        <!-- Bouton supprimer la ligne -->
        <div class="col-auto">
            <button type="button" class="btn btn-link p-0" onclick="supprimerLigne(${index})">
                <i class="bi bi-dash-circle fs-4 text-danger"></i>
            </button>
        </div>

        <!-- Libelle -->
        <div class="col-3">
            <input type="text" class="form-control" name="detailList[${index}].libelle" placeholder="Libellé"/>
        </div>

        <!-- Quantité -->
        <div class="col-1">
            <input type="number" class="form-control" id="quantite_${index}" name="detailList[${index}].quantite" placeholder="Qté" min="1" onchange="calculerMontant(${index})"/>
        </div>

        <!-- Unité -->
        <div class="col-2">
            <input type="text" class="form-control" name="detailList[${index}].unite" placeholder="Unité"/>
        </div>

        <!-- Prix unitaire -->
        <div class="col-2">
            <input type="number" step="0.01" class="form-control" id="prixUnitaire_${index}" name="detailList[${index}].prixUnitaire" placeholder="Prix unitaire" min="0" onchange="calculerMontant(${index})"/>
        </div>

        <!-- Montant -->
        <div class="col-2">
            <input type="text" class="form-control" id="montant_${index}" placeholder="Montant" readonly/>
        </div>
    `;

    container.appendChild(ligne);
    index++;
});

function supprimerLigne(i) {
    const ligne = document.getElementById("ligne_" + i);
    if (ligne) ligne.remove();
}

function calculerMontant(i) {
    const quantite = parseFloat(document.getElementById("quantite_" + i).value) || 0;
    const prix = parseFloat(document.getElementById("prixUnitaire_" + i).value) || 0;
    document.getElementById("montant_" + i).value = (quantite * prix).toFixed(2);
}