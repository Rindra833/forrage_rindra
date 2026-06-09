<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des devis</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/app.css">
</head>
<body>
    <%@ include file="/WEB-INF/jsp/common/navbar.jspf" %>
    <main class="app-shell">
        <div class="app-header">
            <div>
                <h1 class="app-title">Liste des devis</h1>
                <p class="app-subtitle">Aperçu des devis générés pour les demandes.</p>
            </div>
            <div class="app-toolbar top">
                <a href="/devis/form" class="btn btn-primary">
                    <i class="bi bi-plus-lg me-1"></i> Nouveau devis
                </a>
            </div>
        </div>

        <c:if test="${not empty success}">
            <div class="alert alert-success" role="alert">
                ${success}
            </div>
        </c:if>

        <div class="app-panel app-hero mb-4">
            <div class="app-hero-inner">
                <span class="app-kicker"><i class="bi bi-journal-text"></i> Documents</span>
                <h2 class="section-title">Devis enregistrés</h2>
                <p class="app-lead mb-0">Chaque devis est rattaché à une demande et conserve son type ainsi que sa date de création.</p>
                <div class="app-summary mt-4">
                    <div class="summary-card">
                        <p class="summary-label">Total devis</p>
                        <p class="summary-value">${fn:length(devis)}</p>
                    </div>
                    <div class="summary-card">
                        <p class="summary-label">Rattachement</p>
                        <p class="summary-value">Demande</p>
                    </div>
                    <div class="summary-card">
                        <p class="summary-label">Détails</p>
                        <p class="summary-value">Lignes</p>
                    </div>
                </div>
            </div>
        </div>

        <c:choose>
            <c:when test="${not empty devis}">
                <div class="app-panel table-card">
                    <table class="table table-hover align-middle">
                        <thead>
                            <tr>
                                <th>Référence</th>
                                <th>Demande</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${devis}" var="d">
                            <tr>
                                <td id="numdevis${d.idDevis}"><strong>${d.reference}</strong></td>
                                <td>${d.demande.reference}</td>
                                <td>${d.date}</td>
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
                            Aucun devis trouvé.
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
    <script src="${pageContext.request.contextPath}/Js/devis.js"></script>
</body>
</html>
