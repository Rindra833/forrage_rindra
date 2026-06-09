
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forrage</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/app.css">
</head>
<body>
    <%@ include file="/WEB-INF/jsp/common/navbar.jspf" %>
    <main class="app-shell app-shell-sm">
        <div class="app-panel app-hero">
            <div class="app-hero-inner">
                <span class="app-kicker"><i class="bi bi-compass"></i> Tableau de bord</span>
                <h1 class="app-title">Gestion de forage</h1>
                <p class="app-lead">
                    Suivi des demandes, des devis et des statuts, avec des alertes de temps visibles et faciles à tester.
                </p>
                <div class="app-summary">
                    <div class="summary-card">
                        <p class="summary-label">Flux principal</p>
                        <p class="summary-value">Demandes</p>
                    </div>
                    <div class="summary-card">
                        <p class="summary-label">Suivi</p>
                        <p class="summary-value">Statuts</p>
                    </div>
                    <div class="summary-card">
                        <p class="summary-label">Livrables</p>
                        <p class="summary-value">Devis</p>
                    </div>
                </div>
                <div class="app-menu">
                    <a href="/demandes/liste" class="app-menu-link">
                        <span class="app-menu-icon"><i class="bi bi-folder2-open"></i></span>
                        <span>
                            <strong>Demandes</strong><br>
                            <small class="text-muted">Créer, consulter et suivre</small>
                        </span>
                    </a>
                    <a href="/devis/liste" class="app-menu-link">
                        <span class="app-menu-icon"><i class="bi bi-receipt"></i></span>
                        <span>
                            <strong>Devis</strong><br>
                            <small class="text-muted">Lister et générer un devis</small>
                        </span>
                    </a>
                    <a href="/statut-demande/ajout" class="app-menu-link">
                        <span class="app-menu-icon"><i class="bi bi-plus-circle"></i></span>
                        <span>
                            <strong>Ajouter un statut</strong><br>
                            <small class="text-muted">Créer une nouvelle étape</small>
                        </span>
                    </a>
                    <a href="/statut-demande/modif" class="app-menu-link">
                        <span class="app-menu-icon"><i class="bi bi-pencil-square"></i></span>
                        <span>
                            <strong>Modifier un statut</strong><br>
                            <small class="text-muted">Ajuster une étape existante</small>
                        </span>
                    </a>
                </div>
            </div>
        </div>
    </main>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
