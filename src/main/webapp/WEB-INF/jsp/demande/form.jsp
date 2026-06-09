<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter une demande</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/app.css">
</head>
<body>
    <%@ include file="/WEB-INF/jsp/common/navbar.jspf" %>
    <main class="app-shell app-shell-sm">
        <div class="app-header">
            <div>
                <h1 class="app-title">Ajouter une demande</h1>
                <p class="app-subtitle">Créer une nouvelle demande de forage.</p>
            </div>
        </div>

        <div class="app-panel">
            <div class="app-panel-header">
                <span class="app-kicker"><i class="bi bi-file-earmark-plus"></i> Saisie</span>
                <h2 class="section-title">Renseignez les informations de base</h2>
                <p class="section-muted">La demande sera enregistrée avec un premier statut automatique.</p>
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
                    <form:form action="/demandes/add" modelAttribute="demande" method="post">
                        
                        <div class="form-group mt-4">
                            <label for="reference">Référence</label>
                            <div class="input-wrapper">
                                <form:input path="reference" cssClass="form-control" id="reference" placeholder="Entrez la référence" />
                            </div>
                            <p class="page-note">Exemple: <code>DEM-001</code></p>
                        </div>

                        <div class="form-group mt-4">
                            <label for="date">Date</label>
                            <div class="input-wrapper">
                                <form:input type="datetime-local" path="date" cssClass="form-control" id="date" placeholder="Entrez la date" />
                            </div>
                            <p class="page-note">Choisissez une date et une heure précises.</p>
                        </div>

                        <div class="form-group mt-4">
                            <label for="demandeur">Client</label>
                            <div class="input-wrapper">
                                <form:select path="demandeur.idClient" cssClass="form-select" id="demandeur">
                                    <form:option value="0" label="-- Sélectionnez un client --" />
                                    <form:options items="${clients}" itemValue="idClient" itemLabel="nom" />
                                </form:select>
                            </div>
                            <p class="page-note">Le client est chargé depuis les données de base.</p>
                        </div>

                        <div class="form-group mt-4">
                            <label for="lieu">Lieu</label>
                            <div class="input-wrapper">
                                <form:input path="lieu" cssClass="form-control" id="lieu" placeholder="Entrez le lieu" />
                            </div>
                        </div>

                        <div class="form-group mt-4">
                            <label for="commune">Commune</label>
                            <div class="input-wrapper">
                                <form:select path="commune.idCommune" cssClass="form-select" id="commune">
                                    <form:option value="0" label="-- Sélectionnez une commune --" />
                                    <form:options items="${communes}" itemValue="idCommune" itemLabel="libelle" />
                                </form:select>
                            </div>
                            <p class="page-note">La commune doit exister en base.</p>
                        </div>

                        <div class="app-toolbar">
                            <button type="submit" class="btn btn-success">
                                <i class="bi bi-check2-circle me-1"></i> Créer une demande
                            </button>
                            <a href="/demandes/liste" class="btn btn-outline-danger">
                                <i class="bi bi-x-lg me-1"></i> Annuler
                            </a>
                        </div>
                    </form:form>
            </div>
        </div>
    </main>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
