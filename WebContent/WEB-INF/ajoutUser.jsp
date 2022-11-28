<%@include file="entete.jsp" %>
	<main>
	<h1>Ajout utilisateur</h1><br/>
<form method="post">
<fieldset>
<legend>Ajout</legend>
<label for="nom">Nom</label><br/>
 <input type="text" name ="nom"/><br/>
 <label for="prenom">Prenom</label><br/>
 <input type="text" name ="prenom"/><br/>
 <label for="login">Login</label><br/>
 <input type="text" name ="login"/><br/>
 <label>Mot de passe</label><br/>
  <input type="password" name ="mdp"/><br/><br/>
   <input class="btn" type="submit" value ="ajouter"/><br/><br/>
</fieldset>
</form>
</main>
</body>
</html>