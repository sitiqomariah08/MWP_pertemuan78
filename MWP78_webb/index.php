<?php
session_start();

if (isset($_COOKIE['remember_me']) && !isset($_SESSION['username'])) {
    $_SESSION['username'] = $_COOKIE['remember_me'];
}

if (isset($_SESSION['username'])) {
    header("Location: profil.php");
    exit();
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
    <div class="bungkus">
        <main id="main">
            <div class="main-logo">
                <img src="aset/icon-x.svg" alt="logo">
            </div>

            <div class="main-action">
                <div class="main-action-text">
                    <h1>Happening now</h1>
                    <br>
                    <h2>Join today.</h2>
                </div>
                <div class="main-action-button">
                    <button>
                        <img src="aset/google-color-svgrepo-com.svg" alt="google">
                        Sign in with Google
                    </button>
                    <button>
                        <img src="aset/apple-173-svgrepo-com (1).svg" alt="apple">
                        Sign in with Apple
                    </button>
                    <div class="divider">
                        <span>or</span>
                    </div>
                    <button id="btn-create-account">
                        Create account
                    </button>
                    <p>By signing up, you agree to the Terms of Service and Privacy Policy, including Cookie Use.</p>
                    <br>
                    <div class="already-have-account">
                        <h4>Already have an account?</h4>
                        <button id="btn-sign-in">
                            Sign in
                        </button>
                    </div>
                </div>
        </main>

        <footer>
            <div class="footer-link">
                <a href="https://about.x.com/en">About</a>
                <a href="https://help.x.com/en/using-x/download-the-x-app">Download the X app</a>
                <a href="https://help.x.com/en">Help Center</a>
                <a href="https://x.com/en/tos">Terms of Service</a>
                <a href="https://x.com/en/privacy">Privacy Policy</a>
                <a href="https://help.x.com/en/rules-and-policies/x-cookies">Cookie Policy</a>
                <a href="https://help.x.com/en/resources/accessibility">Accessibility</a>
                <a href="https://business.x.com/en/help/troubleshooting/how-x-ads-work">Ads info</a>
                <a href="https://blog.x.com/">Blog</a>
                <a href="https://careers.x.com/en">Careers</a>
                <a href="https://about.x.com/en/who-we-are/brand-toolkit">Brand Resource</a>
                <a href="https://business.x.com/en/advertising?ref=gl-tw-tw-twitter-advertise">Advertising</a>
                <a href="https://business.x.com/en">Marketing</a>
                <a
                    href="https://business.x.com/en?ref=web-twc-ao-gbl-twitterforbusiness&utm_source=twc&utm_medium=web&utm_campaign=ao&utm_content=twitterforbusiness">X
                    for Business</a>
                <a href="https://developer.x.com/en">Developers</a>
                <a href="https://x.com/i/directory/profiles">Directory</a>
                <a href="https://x.com/settings/account/personalization">Settings</a>\
                <p>© 2025 X Corp.</p>
            </div>
        </footer>

        <div id="modal-sign-up">
            <div class="modal">
                <div class="modal-top">
                    <img src="aset/close-svgrepo-com.svg" alt="close" id="close">
                    <img src="aset/icon-x.svg" alt="logo">
                </div>

                <div class="modal-form">
                    <h1>Create your account</h1>
                    <div class="modal-form-input">
                        <form method="post" action="register.php">
                            <input type="text" class="form-control" placeholder="Username" name="username" required>
                            <input type="password" class="form-control" placeholder="Password" name="password"
                                required><br>
                            <button type="submit" class="btn-input" id="create">Create</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <div id="modal-sign-in">
            <div class="modal-in">
                <div class="modal-top-in">
                    <img src="aset/close-svgrepo-com.svg" alt="close" id="close-in">
                    <img src="aset/icon-x.svg" alt="logo">
                    <br><br><br><br>
                </div>

                <div class="modal-form-in">
                    <h1>Login your account</h1>
                    <br>
                    <div class="modal-form-input-in">
                        <form method="post" action="login.php">
                            <input type="text" class="form-control-in" name="username" placeholder="Username">
                            <input type="password" class="form-control-in" name="password" placeholder="Password"><br>

                            <label>
                                <input type="checkbox" name="remember"> Ingat saya
                            </label><br>

                            <button class="btn-input-in" id="next-in" type="submit">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const savedUsername = localStorage.getItem("last_username");
            if (savedUsername) {
                document.querySelector("input[name='username']").value = savedUsername;
            }
        });

        $(function() {
            $("#btn-create-account").click(function() {
                $("#modal-sign-up").fadeIn().css("display", "flex");
            })

            $("#close").click(function() {
                $("#modal-sign-up").fadeOut();
            })
        })

        $(function() {
            $("#btn-sign-in").click(function() {
                $("#modal-sign-in").fadeIn().css("display", "flex");
            })

            $("#close-in").click(function() {
                $("#modal-sign-in").fadeOut();
            })
        })
    </script>
</body>

</html>