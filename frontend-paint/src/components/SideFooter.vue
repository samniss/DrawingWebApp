<template>
  <div id="footer-container">
  <!-- Site footer -->
    <footer class="site-footer">
      <div class="container">
        <div class="row">
          <div style="margin:20px">
            <h2>About</h2>
            <p class="text-justify">Drawing Web Application</p>
          </div>

          <div style="margin:20px">
            <h2>Quick Links</h2>
            <ul class="footer-links">
              <li><a href="https://github.com/samniss/DrawingWebApp" style="font-size: 15px">About Us</a></li> <!-- Mohamed elSamni Repo -->
              <li><a href="https://github.com/MohamedMetwalli5/Paint" style="font-size: 15px">Contact Us</a></li> <!-- My Repo (Mohamed Metwalli) -->
            </ul>
          </div>
        </div>
        <hr>
      </div>
      <div class="container">
        <div class="row">
          <div style="margin:20px">
            <p class="copyright-text">Copyright &copy; 2020 All Rights Reserved by 
             <a href="https://github.com/MohamedMetwalli5/Paint">Paint</a>. <!-- My Repo (Mohamed Metwalli) -->
            </p>
          </div>
        </div>
      </div>
</footer>
  </div>
</template>

<script>
export default {
  name: "SideFooter",
  props: {
    msg: String,
  },
  data() {
    return {
    };
  },
  methods: {
  },
};
</script>

<style scoped>
.site-footer{
  background-color:#26272b;
  padding:20px 0 10px;
  font-size:15px;
  line-height:24px;
  border-radius: 20px;
  margin-left: 10px;
  color:#737373;
  height: 1000px;
}
.site-footer hr
{
  border-top-color:#bbb;
  opacity:0.5
}
.site-footer hr.small
{
  margin:20px 0
}
.site-footer h2
{
  color:#fff;
  font-size:16px;
  text-transform:uppercase;
  margin-top:5px;
  letter-spacing:2px
}
.site-footer a
{
  color:#737373;
}

.footer-links
{
  padding-left:0;
  list-style:none
}
.footer-links li
{
  display:block
}
.footer-links a
{
  color:#737373
}

.footer-links.inline li
{
  display:inline-block
}
.site-footer .social-icons
{
  text-align:left
}
.site-footer .social-icons a
{
  width:40px;
  height:40px;
  line-height:40px;
  margin-left:6px;
  margin-right:0;
  border-radius:100%;
  background-color:#33353d
}
.copyright-text
{
  margin:0
}

@media (max-width:767px)
{
  .copyright-text,.site-footer .social-icons
  {
    text-align:center
  }
}

</style>
