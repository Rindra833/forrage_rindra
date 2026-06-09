function pop_up(id){
    const numdemande=document.getElementById('numdemande'+id).textContent;
    if (confirm("Voulez-vous vraiment supprimer la demande "+numdemande+" ?")) {
        window.location.href = "/demandes/delete/" + id;
    }

    return false;
}
