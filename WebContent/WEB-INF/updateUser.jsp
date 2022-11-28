<%@include file="entete.jsp" %>
	<main>
	<h1>Modification utilisateur</h1>
	
<form method="post">
<fieldset>
<legend>Modifier</legend>
<label for="nom">Nom</label><br/>
 <input type="text" name ="nom" value=${utilisateur.nom}><br/>
 <label for="prenom">Prenom</label><br/>
 <input type="text" name ="prenom" value=${utilisateur.prenom}><br/>
 <label for="login">Login</label><br/>
 <input type="text" name ="login" value=${utilisateur.login}><br/>
 <label>Mot de passe</label><br/>
  <input type="password" name ="mdp" value=${utilisateur.password}><br/><br/>
   <input  class="btn" type="submit" value ="Modifier"><br/>
</fieldset>
</main>
</form>
</body>
</html>