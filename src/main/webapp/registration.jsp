<!DOCTYPE html>
<html lang="en" >
<head>
	<meta charset="UTF-8">
	<title>Jhay Commerce | registration</title>
	<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.2.0/css/all.css'>
	<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.2.0/css/fontawesome.css'>
	<link rel="stylesheet" href="css/registration.css">
	<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
	<input type="hidden" id="status1" value="<%= request.getAttribute("status1")%>">
	<div class="main">
		<!-- Sign up form -->
		<section class="signup">
			<div class="screen__content mb-4">
				<a href="index.jsp" class="text-decoration-none">
					<span class="h1 text-uppercase text-primary bg-dark px-2 font-weight-bold">Jhay</span>
					<span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1 font-weight-bold">Commerce</span>
				</a>
			</div>
			<div class="screen">
					<div class="screen__content">

						<form method="post" action="register" class="register-form" id="register-form">
							<h2 class="form-title">Create New Account</h2>
							<div class="signup">
								<span class="signup__text">Already have an account?</span>
								<a href="login.jsp" class="button signup__button">
									<span class="button__text">Login</span>
									<i class="button__icon fas fa-chevron-right"></i>
								</a>
							</div>
							<div class="form-group">
								<i class="login__icon fas fa-user"></i>
								<label for="first_name"></label><input type="text" class="login__input" name="first_name" id="first_name" placeholder="first name" />
							</div>
							<div class="form-group">
								<i class="login__icon fas fa-user"></i>
								<label for="last_name"></label><input type="text" class="login__input" name="last_name" id="last_name" placeholder="last name" />
							</div>
							<div class="form-group">
								<i class="login__icon fas fa-user"></i>
								<label for="phone_number"></label><input type="text" class="login__input" name="phone_number" id="phone_number" placeholder="Phone Number" />
							</div>
							<div class="form-group">
								<i class="login__icon fas fa-user"></i>
								<label for="email"></label><input type="email" class="login__input" name="email" id="email" placeholder="Email" />
							</div>
							<div class="form-group">
								<i class="login__icon fas fa-lock"></i>
								<label for="password"></label><input type="password" class="login__input" name="password" id="password" placeholder="Password" />
							</div>
							<div class="form-group">
								<i class="login__icon fas fa-lock"></i>
								<label for="re_password"></label><input type="password" class="login__input" name="re_password" id="re_password" placeholder="Repeat password" />
							</div>
								<button class="button login__submit">
									<span class="button__text">Register</span>
									<i class="button__icon fas fa-chevron-right"></i>
								</button>
							</form>
					</div>
				</div>
		</section>

		<!-- JS -->
		<script src="js/main.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
		let status = document.getElementById("status").value;
		if (status === "success") {
			Swal.fire(
					'Welcome Onboard',
					'Proceed to Login',
					'success'
			)
		}else if(status ==="failed"){
			Swal.fire(
					'Registration Failed',
					'Email is already registered',
					'error'
			)
		}
		let status1 = document.getElementById("status1").value;
		if (status1 === "failed") {
			Swal.fire(
					'Failed registration',
					'Password do not match',
					'error'
			)
		}
	</script>

	</div>
</body>
</html>