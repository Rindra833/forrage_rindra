<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter une nouvelle statut de la demande</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/app.css">
</head>
<body>
    <%@ include file="/WEB-INF/jsp/common/navbar.jspf" %>
    <main class="app-shell app-shell-sm">
        <div class="app-header">
            <div>
                <h1 class="app-title">Ajouter un statut de demande</h1>
                <p class="app-subtitle">Créer une étape d'historique liée à une demande.</p>
            </div>
        </div>

        <div class="app-panel app-hero mb-4">
            <div class="app-hero-inner">
                <span class="app-kicker"><i class="bi bi-plus-circle"></i> Historique</span>
                <h2 class="section-title">Créer un nouveau suivi</h2>
                <p class="app-lead mb-0">Saisissez une référence de demande existante pour y ajouter un statut et une observation.</p>
                <div class="app-summary mt-4">
                    <div class="summary-card">
                        <p class="summary-label">Entrée</p>
                        <p class="summary-value">Référence</p>
                    </div>
                    <div class="summary-card">
                        <p class="summary-label">Sortie</p>
                        <p class="summary-value">Statut</p>
                    </div>
                    <div class="summary-card">
                        <p class="summary-label">Données</p>
                        <p class="summary-value">Historique</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="app-form-layout">
            <aside class="app-side-panel">
                <span class="app-kicker"><i class="bi bi-lightning-charge"></i> Conseils</span>
                <h2 class="section-title">Préparer l'ajout</h2>
                <p class="app-lead">Ce formulaire ajoute une étape à l'historique d'une demande déjà créée. La référence doit exister dans la base.</p>
                <ul class="checklist">
                    <li><i class="bi bi-check2-circle"></i><span>Tapez une référence exacte, par exemple <strong>DEM-001</strong>.</span></li>
                    <li><i class="bi bi-check2-circle"></i><span>Choisissez une date si vous voulez contrôler la durée calculée.</span></li>
                    <li><i class="bi bi-check2-circle"></i><span>Sélectionnez un statut depuis la liste chargée automatiquement.</span></li>
                </ul>
                <div class="mini-metric">
                    <div class="mini-metric-item"><span>Couleurs</span><strong>J / R</strong></div>
                    <div class="mini-metric-item"><span>Heures prises</span><strong>08:00 - 16:00</strong></div>
                    <div class="mini-metric-item"><span>Repos</span><strong>Week-end exclu</strong></div>
                </div>
            </aside>

            <div class="app-panel">
                <div class="app-panel-header">
                    <h2 class="section-title">Saisie d'un statut</h2>
                    <p class="section-muted">La référence de demande permet de charger l'historique existant avant enregistrement.</p>
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
                    <form:form action="/statut-demande/add" modelAttribute="statutDemande" method="post">

                        <div class="field-card">
                            <label for="demande">Demande</label>
                            <div class="input-wrapper">
                                <form:input path="demande.reference" cssClass="form-control" id="referenceDemande" placeholder="Entrez la reference de la demande" />
                            </div>

                            <div id="resultatDemande" class="mt-2"></div>
                            <p class="page-note">Commencez par saisir une référence déjà enregistrée.</p>
                        </div>

                        <div class="field-card mt-3">
                            <label for="date">Date</label>
                            <div class="input-wrapper">
                                <form:input type="datetime-local" path="date" cssClass="form-control" id="date" placeholder="Entrez la date" />
                            </div>
                        </div>

                        <div class="field-card mt-3">
                            <label for="observation">Observation</label>
                            <div class="input-wrapper">
                                <form:input path="observation" cssClass="form-control" id="observation" placeholder="Entrez l'observation" />
                            </div>
                        </div>

                        <div class="field-card mt-3">
                            <label for="typesDevis">Statut</label>
                            <div class="input-wrapper">
                                <form:select path="statut.idStatut" cssClass="form-select">
                                    <form:option value="0" label="-- Sélectionnez une statut --" />
                                    <form:options items="${statuts}" itemValue="idStatut" itemLabel="libelle" />
                                </form:select>
                            </div>
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-success">
                                <i class="bi bi-check2-circle me-1"></i> Créer le statut
                            </button>
                            <a href="/" class="btn btn-danger">
                                <i class="bi bi-x-lg me-1"></i> Annuler
                            </a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </main>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/Js/demande.js"></script>
</body>
</html>
