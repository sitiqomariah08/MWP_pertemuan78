<?php
session_start();
if (!isset($_SESSION['username'])) {
    header("Location: index.php");
    exit();
}

include 'koneksi.php';

$jumlahPostingan = 0;
if (isset($_SESSION['username'])) {
    $username = $_SESSION['username'];
    $query = "SELECT COUNT(*) FROM postingan WHERE username = $1";
    $result = pg_query_params($conn, $query, array($username));
    if ($result) {
        $row = pg_fetch_row($result);
        $jumlahPostingan = $row[0];
    }
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="Siti Qomariah">
    <meta name="keywords" content="X, Media Sosial">
    <meta name="robots" content="follow, X, index">

    <title>ria</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>

<body>
    <div class="bungkus">
        <div class="kiri">
            <div class="jarak_kiri">
                <img src="aset/twitter (2).png" class="logo">

                <div class="home">
                    <img src="aset/home.png" alt="home" id="home">
                    <h6 id="ya">Home</h6>
                </div>

                <div class="search">
                    <img src="aset/search.png" alt="search" id="search">
                    <h6 id="ya">Explore</h6>
                </div>

                <div class="bel">
                    <img src="aset/bell.png" alt="bel" id="bel">
                    <h6 id="ya">Notifications</h6>
                </div>

                <div class="email">
                    <img src="aset/email.png" alt="email" id="email">
                    <h6 id="ya">Message</h6>
                </div>

                <div class="grok">
                    <img src="aset/grok.png" alt="grok" id="grok">
                    <h6 id="ya">Grok</h6>
                </div>

                <div class="bookmark">
                    <img src="aset/bookmark.png" alt="bookmark" id="bookmark">
                    <h6 id="ya">Bookmark</h6>
                </div>

                <div class="briefcase">
                    <img src="aset/briefcase.png" alt="briefcase" id="briefcase">
                    <h6 id="ya">Jobs</h6>
                </div>

                <div class="communities">
                    <img src="aset/user-avatar.png" alt="communities" id="communities">
                    <h6 id="ya">Communities</h6>
                </div>

                <div class="premium">
                    <img src="aset/guarantee.png" alt="premium" id="premium">
                    <h6 id="ya">Premium</h6>
                </div>

                <div class="verified">
                    <img src="aset/lightning.png" alt="verified" id="verified">
                    <h6 id="ya">Verified Orgs</h6>
                </div>

                <a href="profil.php" class="home-link">
                    <div class="profile">
                        <img src="aset/user.png" alt="profile" id="profile">
                        <h6 id="ya">Profile</h6>
                    </div>
                </a>

                <div class="more">
                    <img src="aset/more.png" alt="more" id="more">
                    <h6 id="ya">More</h6>
                </div><br>

                <div>
                    <button class="postingBtn" id="postingBtn">
                        <h4 id="pos">Posting</h4>
                    </button>
                </div><br><br><br>

                <div class="profileee">
                    <img src="aset/agustd.jpeg" alt="profileee" id="photo">
                    <div class="profile-text">
                        <h4 id="nama"><?php echo $_SESSION['username']; ?></h4>
                        <h4 id="user"><?php echo $_SESSION['username']; ?></h4>
                    </div>
                    <img src="aset/text.png" alt="elip" class="elipsis" id="btn-logout">
                </div>
            </div>
        </div>

        <div class="tengah">
            <div class="konten">
                <?php
                include 'koneksi.php';

                $query = pg_query($conn, "SELECT * FROM postingan ORDER BY tanggal_post DESC");

                while ($post = pg_fetch_assoc($query)) {
                    $id_post = $post['id'];
                    $username = htmlspecialchars($post['username']);
                    $isi = nl2br(htmlspecialchars($post['isi']));
                    $tanggal = $post['tanggal_post'];

                    $replies = pg_query_params($conn, "SELECT * FROM reply WHERE id_postingan = $1 ORDER BY tanggal_reply ASC", array($id_post));
                    $jumlah_reply = pg_num_rows($replies);

                    echo '<div class="postingan">';
                    echo '  <div class="post-kiri">';
                    echo '      <img src="aset/default_profile_400x400.png" alt="profile">';
                    echo '  </div>';
                    echo '  <div class="post-kanan">';
                    echo "      <h3>$username</h3>";
                    echo "      <p>$isi</p>";

                    echo '      <div class="postingan-reply">';
                    echo '          <div class="postingan-reply-jumlah">';
                    echo '              <img src="aset/comment-1-svgrepo-com.svg" class="btn-reply" 
            data-id="' . $id_post . '" 
            data-username="' . $username . '" 
            data-isi="' . htmlspecialchars($post['isi']) . '">';
                    echo $jumlah_reply > 0 ? '<p>' . $jumlah_reply . '</p>' : '<p></p>';
                    echo '          </div>';
                    echo "          <small>$tanggal</small>";
                    echo '      </div>';

                    if ($jumlah_reply > 0) {
                        while ($rep = pg_fetch_assoc($replies)) {
                            echo '<div class="balasan">';
                            echo '<strong>' . htmlspecialchars($rep['username']) . '</strong>: ';
                            echo nl2br(htmlspecialchars($rep['isi']));
                            echo '</div>';
                        }
                    }

                    echo '  </div>';
                    echo '</div>';
                }
                ?>
            </div>
        </div>

        <div class="kanan">
            <div class="searchBar">
                <img src="aset/search.png" id="cari" />
                <h6 id="teksSearch">Search</h6>
            </div>

            <div class="bubble" id="rekom">
                <h1 id="you">You might like</h1>
                <div class="mungkinSuka">
                    <img src="aset/jiso.jpg" alt="Profile" id="photo" />
                    <div class="profilee-text">
                        <h4 id="namaa">Johns Charts</h4>
                        <h4 id="userr">@johncharts</h4>
                    </div>
                    <button class="follow">Follow</button>
                </div>

                <div class="mungkinSuka">
                    <img src="aset/lisa.jpg" alt="Profile" id="photo" />
                    <div class="profilee-text">
                        <h4 id="namaa">NYSE</h4>
                        <h4 id="userr">@nyswy</h4>
                    </div>
                    <button class="follow">Follow</button>
                </div>

                <div class="mungkinSuka">
                    <img src="aset/rm.jpg" alt="Profile" id="photo" />
                    <div class="profilee-text">
                        <h4 id="namaa">RM</h4>
                        <h4 id="userr">@BTS_twt_RMJOON</h4>
                    </div>
                    <button class="follow">Follow</button>
                </div>

                <div class="lebihBanyak" id="moreItem">
                    <h5>Show more</h5>
                </div>
            </div>

            <div class="bubble">
                <h1 id="hangatt">What's happening</h1>
                <div class="lebihBanyak">
                    <span class="kategori_trend">Trending in Indonesia</span>
                    <p class="nama_trend">Pepaya</p>
                    <span class="jumlah_trend">213K posts</span>
                    <span class="opsi_lainnya">•••</span>
                </div>

                <div class="lebihBanyak">
                    <span class="kategori_trend">Politics · Trending</span>
                    <p class="nama_trend">Pertamax</p>
                    <span class="jumlah_trend">209K posts</span>
                    <span class="opsi_lainnya">•••</span>
                </div>

                <div class="lebihBanyak">
                    <span class="kategori_trend">Trending in Indonesia</span>
                    <p class="nama_trend">Hujan</p>
                    <span class="jumlah_trend">112K posts</span>
                    <span class="opsi_lainnya">•••</span>
                </div>

                <div class="lebihBanyak">
                    <span class="kategori_trend">Trending in Korea</span>
                    <p class="nama_trend">Suga</p>
                    <span class="jumlah_trend">567K posts</span>
                    <span class="opsi_lainnya">•••</span>
                </div>

                <div class="lebihBanyak" id="moreItem">
                    <h5>Show more</h5>
                </div>
            </div>
            <br />

            <footer>
                <div class="bawah_sendiri">
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
                    <a href="https://x.com/settings/account/personalization">Settings</a>
                </div>
                <p>© 2025 X Corp.</p>
            </footer>
        </div>

        <div class="grok_bawah">
            <img src="aset/grok.png" alt="GROK" />
            <span>GROK</span>
        </div>

        <div class="pesan-bar">
            <span class="pesan-title">Pesan</span>
            <div class="pesan-icons">
                <i class="fas fa-envelope"></i>
                <i class="fas fa-chevron-up"></i>
            </div>
        </div>
    </div>

    <!-- modals -->
    <div id="modal-post">
        <div class="post">
            <div class="post-top">
                <img src="aset/close-svgrepo-com.svg" alt="" class="close" id="close">
                <h6>Draf</h6>
            </div>

            <div class="post-form">
                <img src="aset/default_profile_400x400.png" alt="profile">
                <textarea placeholder="Apa yang terjadi?"></textarea>
            </div>

            <div class="post-bottom">
                <button>Posting</button>
            </div>
        </div>
    </div>

    <!-- modals reply-->
    <div id="modal-reply">
        <div class="reply">
            <div class="reply-top">
                <img src="aset/close-svgrepo-com.svg" alt="" class="close" id="close">
                <h6>Draf</h6>
            </div>

            <div class="reply-preview"></div>

            <div class="reply-form">
                <img src="aset/default_profile_400x400.png" alt="profile">
                <textarea placeholder="Tambahkan postingan lainnya"></textarea>
            </div>

            <div class="reply-bottom">
                <button>Posting</button>
            </div>
        </div>
    </div>

    <!-- notif -->
    <div id="toast" class="toast">Postingan berhasil!</div>

    <!-- log out -->
    <div class="logout" id="logout-popup">
        <a href="logout.php" class="logout-popup">Log Out</a>
    </div>

    <script>
        $(function() {
            $("#postingBtn").click(function() {
                $("#modal-post").fadeIn().css("display", "flex");
            })

            $("#close").click(function() {
                $("#modal-post").fadeOut();
            })
        })

        // posting
        document.addEventListener("DOMContentLoaded", function() {
            const textarea = document.querySelector("#modal-post textarea");
            const postBtn = document.querySelector(".post-bottom button");

            textarea.addEventListener('input', () => {
                const isKosong = textarea.value.trim() === '';
                postBtn.disabled = isKosong;

                if (isKosong) {
                    textarea.style.backgroundColor = '';
                    textarea.style.color = '';
                    postBtn.style.backgroundColor = 'rgba(0, 0, 0, 0.2)';
                } else {
                    postBtn.style.backgroundColor = 'black';
                }
            });

            postBtn.addEventListener("click", function() {
                const isi = textarea.value.trim();

                if (isi !== "") {
                    fetch("post.php", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/x-www-form-urlencoded"
                            },
                            body: `isi=${encodeURIComponent(isi)}`
                        })
                        .then(response => response.text())
                        .then(data => {
                            if (data === 'sukses') {
                                textarea.value = "";
                                postBtn.disabled = true;
                                postBtn.style.backgroundColor = 'rgba(0, 0, 0, 0.2)';
                                document.getElementById("close").click();
                                showToast("Postingan berhasil dikirim!");
                            } else {
                                showToast("Gagal menyimpan postingan.");
                            }
                        });
                }
            });

            function showToast(message) {
                const toast = document.getElementById("toast");
                toast.textContent = message;
                toast.classList.add("show");
                setTimeout(() => {
                    toast.classList.remove("show");
                }, 3000);
            }
        });

        // reply
        document.addEventListener("DOMContentLoaded", function() {
            const modalReply = document.getElementById("modal-reply");
            const replyTextarea = modalReply.querySelector("textarea");
            const replyPreview = modalReply.querySelector(".reply-preview");
            const replyBtn = modalReply.querySelector("button");

            // Tambahkan listener input untuk mengatur tombol
            replyTextarea.addEventListener("input", function() {
                const isKosong = replyTextarea.value.trim() === '';
                replyBtn.disabled = isKosong;
                replyBtn.style.backgroundColor = isKosong ? 'rgba(0, 0, 0, 0.2)' : 'black';
            });

            document.querySelector(".konten").addEventListener("click", function(e) {
                if (e.target.classList.contains("btn-reply")) {
                    const username = e.target.getAttribute("data-username");
                    const isi = e.target.getAttribute("data-isi");
                    const id = e.target.getAttribute("data-id");

                    modalReply.setAttribute("data-id", id);
                    replyPreview.innerHTML = `<div class="preview"><strong>${username}</strong>: ${isi}</div>`;
                    replyTextarea.value = "";
                    replyBtn.disabled = true; // saat modal dibuka, tombol disable dulu
                    replyBtn.style.backgroundColor = 'rgba(0, 0, 0, 0.2)';
                    modalReply.style.display = "flex";
                }
            });

            modalReply.querySelector(".close").addEventListener("click", function() {
                modalReply.style.display = "none";
                replyTextarea.value = "";
                replyPreview.innerHTML = "";
            });

            replyBtn.addEventListener("click", function() {
                const isi = replyTextarea.value.trim();
                const id = modalReply.getAttribute("data-id");

                if (isi !== "") {
                    fetch("reply.php", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/x-www-form-urlencoded"
                            },
                            body: `isi=${encodeURIComponent(isi)}&id=${id}`
                        })
                        .then(res => res.text())
                        .then(data => {
                            if (data === "sukses") {
                                location.reload();
                            } else {
                                alert("Gagal mengirim reply.");
                            }
                        });
                } else {
                    alert("Balasan tidak boleh kosong!");
                }
            });
        });

        document.getElementById("btn-logout").addEventListener("click", function(e) {
            const popup = document.getElementById("logout-popup");
            popup.style.display = (popup.style.display === "flex") ? "none" : "flex";

            e.stopPropagation();
        });

        document.addEventListener("click", function(e) {
            const popup = document.getElementById("logout-popup");
            const btn = document.getElementById("btn-logout");

            if (!popup.contains(e.target) && !btn.contains(e.target)) {
                popup.style.display = "none";
            }
        });
    </script>
</body>

</html>