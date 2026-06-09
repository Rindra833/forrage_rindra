<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier une statut de la demande</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/app.css">
</head>
<body>
    <%@ include file="/WEB-INF/jsp/common/navbar.jspf" %>
    <main class="app-shell app-shell-sm">
        <div class="app-header">
            <div>
                <h1 class="app-title">Modifier un statut de demande</h1>
                <p class="app-subtitle">Retrouver un statut existant puis ajuster la date ou l'observation.</p>
            </div>
        </div>

        <div class="app-panel app-hero mb-4">
            <div class="app-hero-inner">
                <span class="app-kicker"><i class="bi bi-pencil-square"></i> Mise à jour</span>
                <h2 class="section-title">Corriger un suivi existant</h2>
                <p class="app-lead mb-0">Choisissez la demande et le statut à modifier, puis ajustez les champs si besoin.</p>
                <div class="app-summary mt-4">
                    <div class="summary-card">
                        <p class="summary-label">Recherche</p>
                        <p class="summary-value">Référence</p>
                    </div>
                    <div class="summary-card">
                        <p class="summary-label">Action</p>
                        <p class="summary-value">Choix statut</p>
                    </div>
                    <div class="summary-card">
                        <p class="summary-label">Mise à jour</p>
                        <p class="summary-value">Date / Obs.</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="app-form-layout">
            <aside class="app-side-panel">
                <span class="app-kicker"><i class="bi bi-sliders"></i> Guide</span>
                <h2 class="section-title">Mettre à jour un historique</h2>
                <p class="app-lead">Sélectionnez la demande, puis le statut à corriger. Le formulaire récupère ensuite la ligne d'historique correspondante.</p>
                <ul class="checklist">
                    <li><i class="bi bi-check2-circle"></i><span>La référence sert de point d'entrée pour charger les statuts de la demande.</span></li>
                    <li><i class="bi bi-check2-circle"></i><span>Choisissez le statut à modifier dans la liste déroulante.</span></li>
                    <li><i class="bi bi-check2-circle"></i><span>Vous pouvez ajuster la date ou l'observation avant validation.</span></li>
                </ul>
                <div class="mini-metric">
                    <div class="mini-metric-item"><span>Action</span><strong>Mise à jour</strong></div>
                    <div class="mini-metric-item"><span>Chargement</span><strong>Automatique</strong></div>
                    <div class="mini-metric-item"><span>Résultat</span><strong>Historique propre</strong></div>
                </div>
            </aside>

            <div class="app-panel">
                <div class="app-panel-header">
                    <h2 class="section-title">Sélectionnez la demande et le statut</h2>
                    <p class="section-muted">Le formulaire charge automatiquement les informations existantes lorsque le statut est choisi.</p>
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
                    <form:form action="/statut-demande/update" modelAttribute="statutDemande" method="post" id="form">

                        <div class="field-card">
                            <label for="demande">Demande</label>
                            <div class="input-wrapper">
                                <form:input path="demande.reference" cssClass="form-control" id="referenceDemande" placeholder="Entrez la reference de la demande" />
                            </div>

                            <div id="resultatDemande" class="mt-2"></div>
                            <p class="page-note">La liste des statuts se met à jour après la saisie de la référence.</p>
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
                                <form:select path="statut.idStatut" cssClass="form-select" id="statuts">
                                    <form:option value="0" label="-- Sélectionnez une statut --" />
                                    <form:options items="${statuts}" itemValue="idStatut" itemLabel="libelle" />
                                </form:select>
                            </div>
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-success">
                                <i class="bi bi-check2-circle me-1"></i> Enregistrer la modification
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
    <script src="${pageContext.request.contextPath}/Js/statutDemande.js"></script>
</body>
</html>
