<template>
  <!--Main Navigation-->
  <header>

      <!-- Navbar -->
      <nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
          <div class="container">

              <!-- Brand -->
              <router-link to="/" class="navbar-brand waves-effect">
                  <strong class="blue-text">Survey</strong>
              </router-link>

              <!-- Collapse -->
              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                  aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
              </button>

              <!-- Links -->
              <div class="collapse navbar-collapse" id="navbarSupportedContent">

                  <!-- Left -->
                  <ul class="navbar-nav mr-auto">
                    <router-link to="/" tag="li" class="nav-item">
                      <a href="#" class="nav-link waves-effect">Home
                        <span class="sr-only">(current)</span>
                      </a>
                    </router-link>

                      <router-link to="/Adm" tag="li" class="nav-item"><a href="#" class="nav-link waves-effect">Admin</a></router-link>
                      <!-- <li class="nav-item">
                          <a class="nav-link waves-effect" href="https://mdbootstrap.com/getting-started/" target="_blank">Free download</a>
                      </li>
                      <li class="nav-item">
                          <a class="nav-link waves-effect" href="https://mdbootstrap.com/education/bootstrap/" target="_blank">Free tutorials</a>
                      </li> -->
                  </ul>

                  <!-- Right -->
                  <ul class="navbar-nav nav-flex-icons">
                      <!-- <li class="nav-item">
                          <a href="https://www.facebook.com/mdbootstrap" class="nav-link waves-effect" target="_blank">
                              <i class="fa fa-facebook"></i>
                          </a>
                      </li>
                      <li class="nav-item">
                          <a href="https://twitter.com/MDBootstrap" class="nav-link waves-effect" target="_blank">
                              <i class="fa fa-twitter"></i>
                          </a>
                      </li> -->
                      <li class="nav-item">
                          <a href="https://github.com/indiflex/nodevue" class="nav-link border border-light rounded waves-effect"
                              target="_blank">
                              <i class="fa fa-github mr-2"></i>Survey GitHub
                          </a>
                      </li>
                  </ul>

              </div>

          </div>
      </nav>
      <!-- Navbar -->

  </header>
  <!--Main Navigation-->
</template>