<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des alertes</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/app.css">
</head>
<body>
    <%@ include file="/WEB-INF/jsp/common/navbar.jspf" %>
    <main class="app-shell">
        <div class="app-header">
            <div>
                <h1 class="app-title">Liste des alertes</h1>
                <p class="app-subtitle">Alertes liées au suivi de la demande.</p>
            </div>
        </div>

        <div class="app-panel app-hero mb-4">
            <div class="app-hero-inner">
                <span class="app-kicker"><i class="bi bi-exclamation-triangle"></i> Suivi temps</span>
                <h2 class="section-title">Lecture des seuils d'alerte</h2>
                <p class="app-lead mb-0">
                    <span class="alert-code alert-code-j me-2">J</span> alerte jaune, 
                    <span class="alert-code alert-code-r me-2">R</span> alerte rouge.
                    Les durées sont calculées en minutes ouvrées.
                </p>
                <div class="app-summary mt-4">
                    <div class="summary-card">
                        <p class="summary-label">Alertes trouvées</p>
                        <p class="summary-value">${fn:length(alertes)}</p>
                    </div>
                    <div class="summary-card">
                        <p class="summary-label">Couleur jaune</p>
                        <p class="summary-value">J</p>
                    </div>
                    <div class="summary-card">
                        <p class="summary-label">Couleur rouge</p>
                        <p class="summary-value">R</p>
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${not empty erreur}">
            <div class="alert alert-danger" role="alert">
                ${erreur}
            </div>
        </c:if>
        <c:choose>
            <c:when test="${not empty alertes}">
                <div class="app-panel table-card">
                    <table class="table table-hover align-middle">
                        <thead>
                            <tr>
                                <th>Statut 1</th>
                                <th>Statut 2</th>
                                <th>Durée</th>
                                <th>Alerte</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${alertes}" var="a">
                            <c:choose>
                                <c:when test="${a.alerte eq 'J'}">
                                    <c:set var="rowClass" value="alert-row-warning" />
                                </c:when>
                                <c:when test="${a.alerte eq 'R'}">
                                    <c:set var="rowClass" value="alert-row-danger" />
                                </c:when>
                                <c:otherwise>
                                    <c:set var="rowClass" value="" />
                                </c:otherwise>
                            </c:choose>
                            <tr class="${rowClass}">
                                <td>
                                    <div class="status-chip">
                                        <strong>${a.statut1.libelle}</strong>
                                        <span>Id ${a.statut1.idStatut}</span>
                                    </div>
                                </td>
                                <td>
                                    <div class="status-chip">
                                        <strong>${a.statut2.libelle}</strong>
                                        <span>Id ${a.statut2.idStatut}</span>
                                    </div>
                                </td>
                                <td><span class="badge-soft badge-soft-success">${a.durer} min</span></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${a.alerte eq 'J'}">
                                            <span class="alert-code alert-code-j">J</span>
                                        </c:when>
                                        <c:when test="${a.alerte eq 'R'}">
                                            <span class="alert-code alert-code-r">R</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge-soft badge-soft-info">${a.alerte}</span>
                                        </c:otherwise>
                                    </c:choose>
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
                            Aucune alerte trouvée.
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="app-toolbar">
            <a href="/demandes/liste" class="btn btn-outline-danger">
                <i class="bi bi-arrow-left me-1"></i> Retour à la liste des demandes
            </a>
        </div>
    </main>

    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/Js/fonction.js"></script>
</body>
</html>
