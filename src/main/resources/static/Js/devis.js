function pop_up(id){
    const numdevis=document.getElementById('numdevis'+id).textContent;
    if (confirm("Voulez-vous vraiment supprimer la devis "+numdevis+" ?")) {
        window.location.href = "/devis/delete/" + id;
    }

    return false;
}
