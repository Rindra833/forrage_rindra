<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un devis</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/app.css">
</head>
<body>
    <%@ include file="/WEB-INF/jsp/common/navbar.jspf" %>
    <main class="app-shell">
        <div class="app-header">
            <div>
                <h1 class="app-title">Ajouter un devis</h1>
                <p class="app-subtitle">Créer un devis avec ses détails de prestation.</p>
            </div>
        </div>

        <div class="app-panel">
            <div class="app-panel-header">
                <span class="app-kicker"><i class="bi bi-receipt"></i> Création</span>
                <h2 class="section-title">Renseignez le devis et ses lignes</h2>
                <p class="section-muted">Une demande existante, un type et au moins une ligne de détail sont attendus.</p>
            </div>
            <div class="app-panel-body">
                    <c:if test="${not empty success}">
                        <div class="alert alert-success" role="alert">
                            ${success}
                        </div>
                    </c:if>
                    <c:if test="${not empty erreur}">
                        <div class="alert alert-danger" role="alert">
                            ${erreur}
                        </div>
                    </c:if>
                    <form:form action="/devis/add" modelAttribute="devis" method="post">

                        <div class="form-group mt-4">
                            <label for="demande">Demande</label>
                            <div class="input-wrapper">
                                <form:input path="demande.reference" cssClass="form-control" id="referenceDemande" placeholder="Entrez la référence de la demande" />
                            </div>

                            <div id="resultatDemande" class="mt-2"></div>
                            <p class="page-note">Saisissez une référence de demande déjà créée pour afficher ses informations.</p>
                        </div>

                        <div class="form-group mt-4">
                            <label for="reference">Référence</label>
                            <div class="input-wrapper">
                                <form:input path="reference" cssClass="form-control" id="reference" placeholder="Entrez la référence du devis" />
                            </div>
                        </div>

                        <div class="form-group mt-4">
                            <label for="typesDevis">Type du devis</label>
                            <div class="input-wrapper">
                                <form:select path="typeDevis.idType" cssClass="form-select" id="typesDevis">
                                    <form:option value="0" label="-- Sélectionnez un type --" />
                                    <form:options items="${typesDevis}" itemValue="idType" itemLabel="libelle" />
                                </form:select>
                            </div>
                        </div>

                        <div class="form-group mt-4">
                            <label for="date">Date</label>
                            <div class="input-wrapper">
                                <form:input type="datetime-local" path="date" cssClass="form-control" id="date" placeholder="Entrez la date" />
                            </div>
                        </div>

                        <div class="mt-4">
                            <h5 class="d-inline">Détails du devis</h5>
                            <button type="button" class="btn btn-outline-secondary btn-sm ms-2" id="btnAjouterLigne">
                                <i class="bi bi-plus-lg me-1"></i> Ligne
                            </button>
                        </div>

                        <%-- Zone où les lignes seront ajoutées dynamiquement --%>
                        <div id="lignesDetails" class="details-box mt-3"></div>
                        <p class="page-note">Ajoutez au moins une ligne avant l'envoi du formulaire.</p>

                        <div class="app-toolbar">
                            <button type="submit" class="btn btn-success">
                                <i class="bi bi-check2-circle me-1"></i> Créer un devis
                            </button>
                            <a href="/devis/liste" class="btn btn-outline-danger">
                                <i class="bi bi-x-lg me-1"></i> Annuler
                            </a>
                        </div>
                    </form:form>
            </div>
        </div>
    </main>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/Js/demande.js"></script>
    <script src="${pageContext.request.contextPath}/Js/detail.js"></script>
</body>
</html>
