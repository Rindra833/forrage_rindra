<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des demandes</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/app.css">
</head>
<body>
    <%@ include file="/WEB-INF/jsp/common/navbar.jspf" %>
    <main class="app-shell">
        <div class="app-header">
            <div>
                <h1 class="app-title">Liste des demandes</h1>
                <p class="app-subtitle">Consultation des demandes enregistrées.</p>
            </div>
            <div class="app-toolbar top">
                <a href="/demandes/form" class="btn btn-primary">
                    <i class="bi bi-plus-lg me-1"></i> Nouvelle demande
                </a>
            </div>
        </div>

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

        <div class="app-panel app-hero mb-4">
            <div class="app-hero-inner">
                <span class="app-kicker"><i class="bi bi-clipboard-data"></i> Vue d'ensemble</span>
                <h2 class="section-title mb-2">Demandes enregistrées</h2>
                <p class="app-lead mb-0">Chaque demande peut être suivie via ses alertes et son historique de statut.</p>
                <div class="app-summary mt-4">
                    <div class="summary-card">
                        <p class="summary-label">Total demandes</p>
                        <p class="summary-value">${fn:length(demandes)}</p>
                    </div>
                    <div class="summary-card">
                        <p class="summary-label">Action principale</p>
                        <p class="summary-value">Alertes</p>
                    </div>
                    <div class="summary-card">
                        <p class="summary-label">Saisie</p>
                        <p class="summary-value">Formulaire</p>
                    </div>
                </div>
            </div>
        </div>

        <c:choose>
            <c:when test="${not empty demandes}">
                <div class="app-panel table-card">
                    <table class="table table-hover align-middle">
                        <thead>
                            <tr>
                                <th>Référence</th>
                                <th>Date</th>
                                <th>Client</th>
                                <th>Lieu</th>
                                <th>Commune</th>
                                <th class="text-end">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${demandes}" var="d">
                        <tr>
                            <td id="numdemande${d.idDemande}"><strong>${d.reference}</strong></td>
                            <td>${d.date}</td>
                            <td>${d.demandeur.nom}</td>
                            <td>${d.lieu}</td>
                            <td>${d.commune.libelle}</td>
                            <td class="text-end">
                                <a href="/demandes/${d.idDemande}/alertes/liste" class="btn btn-outline-info btn-sm">
                                    <i class="bi bi-bell me-1"></i> Alertes
                                </a>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>

            <c:otherwise>
                <div class="app-panel">
                    <div class="app-panel-body">
                        <div class="alert alert-info mb-0">
                            Aucune demande trouvée.
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="app-toolbar">
            <a href="/" class="btn btn-outline-danger">
                <i class="bi bi-arrow-left me-1"></i> Retour au menu
            </a>
        </div>
    </main>

    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/Js/fonction.js"></script>
</body>
</html>
