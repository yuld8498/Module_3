<header id="topnav">
    <!-- Topbar Start -->
    <div class="navbar-custom">
        <div class="container-fluid">
            <!-- LOGO -->
            <div class="logo-box">
                <a href="#" class="logo text-centeo">
                            <span class="logo-lg">
                                <img src="logo.png" alt="" height="50">
                                <!-- <span class="logo-lg-text-light">Zircos</span> -->
                            </span>
                    <span class="logo-sm">
                                <!-- <span class="logo-sm-text-dark">Z</span> -->
                                <img src="logo.png" alt="" height="50">
                            </span>
                </a>
            </div>

            <ul class="list-unstyled topnav-menu mb-0">

                <li class="dropdown notification-list">
                    <!-- Mobile menu toggle-->
                    <a class="navbar-toggle nav-link">
                        <div class="lines">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                    </a>
                    <!-- End mobile menu toggle-->
                </li>
                <li class="dropdown notification-list" style="margin-right: 20px;">
                    <a class="nav-link dropdown-toggle"
                       href="/product" role="button"
                       aria-expanded="false">
                        <div>Home Page</div>
                    </a>
                </li>
                <li class="dropdown notification-list" style="margin-right: 20px;">
                    <a class="nav-link dropdown-toggle"
                       href="/product" role="button"
                       aria-expanded="false">
                        <div>Product </div>
                    </a>
                </li>
                <li class="dropdown notification-list" style="margin-right: 20px;">
                    <a class="nav-link dropdown-toggle"
                       href="/product?action=create" role="button"
                       aria-expanded="false">
                        <div>Create Product </div>
                    </a>
                </li>
<%--                <ul class="list-unstyled topnav-menu topnav-menu-left m-0">--%>

<%--                    <li class="d-none d-sm-block">--%>
<%--                        <form class="app-search">--%>
<%--                            <div class="app-search-box">--%>
<%--                                <div class="input-group">--%>
<%--                                    <input type="text" class="form-control" placeholder="Search...">--%>
<%--                                    <div class="input-group-append">--%>
<%--                                        <button class="btn" type="submit">--%>
<%--                                            <i class="fas fa-search"></i>--%>
<%--                                        </button>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </form>--%>
<%--                    </li>--%>
<%--                </ul>--%>
                <li class="dropdown notification-list float-right">
                    <a class="nav-link dropdown-toggle nav-user mr-0 waves-effect waves-light"
                       data-toggle="dropdown" href="#" role="button" aria-haspopup="false"
                       aria-expanded="false">
                        <i class="mdi mdi-settings-outline noti-icon"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right profile-dropdown ">
                        <!-- item-->
                        <div class="dropdown-header noti-title">
                            <h6 class="text-overflow m-0">Welcome !</h6>
                        </div>

                        <!-- item-->
                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                            <i class="mdi mdi-account-outline"></i>
                            <span>Profile</span>
                        </a>

                        <!-- item-->
                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                            <i class="mdi mdi-settings-outline"></i>
                            <span>Settings</span>
                        </a>

                        <!-- item-->
                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                            <i class="mdi mdi-lock-outline"></i>
                            <span>Lock Screen</span>
                        </a>

                        <div class="dropdown-divider"></div>

                        <!-- item-->
                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                            <i class="mdi mdi-logout-variant"></i>
                            <span>Logout</span>
                        </a>

                    </div>
                </li>

            </ul>

            <div class="clearfix"></div>
        </div>
    </div>
    <!-- end Topbar -->

    <div class="topbar-menu">
        <div class="container-fluid">
            <div id="navigation">
                <!-- Navigation Menu-->
                <ul class="navigation-menu">

                    <li class="has-submenu">
                        <a href="#"> <i class="mdi mdi-view-dashboard"></i>Dashboard</a>
                        <ul class="submenu">
                            <li><a href="index.html">Dashboard 1</a></li>
                            <li><a href="dashboard_2.html">Dashboard 2</a></li>
                        </ul>
                    </li>

                    <li class="has-submenu">
                        <a href="#">
                            <i class="mdi mdi-diamond-stone"></i>Components
                        </a>
                        <ul class="submenu">
                            <li class="has-submenu">
                                <a href="#">Forms <div class="arrow-down"></div></a>
                                <ul class="submenu">
                                    <li><a href="form-elements.html">Form Elements</a></li>
                                    <li><a href="form-advanced.html">Form Advanced</a></li>
                                    <li><a href="form-validation.html">Form Validation</a></li>
                                    <li><a href="form-pickers.html">Form Pickers</a></li>
                                    <li><a href="form-wizard.html">Form Wizard</a></li>
                                    <li><a href="form-mask.html">Form Masks</a></li>
                                    <li><a href="form-summernote.html">Summernote</a></li>
                                    <li><a href="form-quilljs.html">Quilljs Editor</a></li>
                                    <li><a href="form-uploads.html">Multiple File Upload</a></li>
                                </ul>
                            </li>
                            <li class="has-submenu">
                                <a href="#">Charts <div class="arrow-down"></div></a>
                                <ul class="submenu">
                                    <li><a href="chart-flot.html">Flot Charts</a></li>
                                    <li><a href="chart-morris.html">Morris Charts</a></li>
                                    <li><a href="chart-google.html">Google Charts</a></li>
                                    <li><a href="chart-chartist.html">Chartist Charts</a></li>
                                    <li><a href="chart-chartjs.html">Chartjs Charts</a></li>
                                    <li><a href="chart-c3.html">C3 Charts</a></li>
                                    <li><a href="chart-sparkline.html">Sparkline Charts</a></li>
                                    <li><a href="chart-knob.html">Jquery Knob</a></li>
                                </ul>
                            </li>

                            <li class="has-submenu">
                                <a href="#">Email <div class="arrow-down"></div></a>
                                <ul class="submenu">
                                    <li><a href="email-inbox.html"> Inbox</a></li>
                                    <li><a href="email-read.html"> Read Mail</a></li>
                                    <li><a href="email-compose.html"> Compose Mail</a></li>
                                </ul>
                            </li>

                            <li class="has-submenu">
                                <a href="#">Icons <div class="arrow-down"></div></a>
                                <ul class="submenu">
                                    <li><a href="icons-colored.html">Colored Icons</a></li>
                                    <li><a href="icons-materialdesign.html">Material Design</a></li>
                                    <li><a href="icons-ionicons.html">Ion Icons</a></li>
                                    <li><a href="icons-fontawesome.html">Font awesome</a></li>
                                    <li><a href="icons-themifyicon.html">Themify Icons</a></li>
                                    <li><a href="icons-typicons.html">Typicons</a></li>
                                </ul>
                            </li>

                            <li class="has-submenu">
                                <a href="#">Tables <div class="arrow-down"></div></a>
                                <ul class="submenu">
                                    <li><a href="tables-basic.html">Basic Tables</a></li>
                                    <li><a href="tables-layouts.html">Tables Layouts</a></li>
                                    <li><a href="tables-datatable.html">Data Table</a></li>
                                    <li><a href="tables-responsive.html">Responsive Table</a></li>
                                    <li><a href="tables-tablesaw.html">Tablesaw Table</a></li>
                                    <li><a href="tables-editable.html">Editable Table</a></li>
                                </ul>
                            </li>

                            <li class="has-submenu">
                                <a href="#">Maps <div class="arrow-down"></div></a>
                                <ul class="submenu">
                                    <li><a href="maps-google.html">Google Maps</a></li>
                                    <li><a href="maps-vector.html">Vector Maps</a></li>
                                    <li><a href="maps-mapael.html">Mapael Maps</a></li>
                                </ul>
                            </li>

                        </ul>
                    </li>

                    <li class="has-submenu">
                        <a href="#">
                            <i class="mdi mdi-google-pages"></i>Pages
                        </a>
                        <ul class="submenu megamenu">
                            <li>
                                <ul>
                                    <li><a href="page-starter.html">Starter Page</a></li>
                                    <li><a href="page-login.html">Login</a></li>
                                    <li><a href="page-register.html">Register</a></li>
                                    <li><a href="page-logout.html">Logout</a></li>
                                    <li><a href="page-recoverpw.html">Recover Password</a></li>
                                </ul>
                            </li>

                            <li>
                                <ul>
                                    <li><a href="page-lock-screen.html">Lock Screen</a></li>
                                    <li><a href="page-confirm-mail.html">Confirm Mail</a></li>
                                    <li><a href="page-404.html">Error 404</a></li>
                                    <li><a href="page-404-alt.html">Error 404-alt</a></li>
                                    <li><a href="page-500.html">Error 500</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>

                    <li class="has-submenu">
                        <a href="#">
                            <i class="mdi mdi-book-multiple"></i>Extras
                        </a>
                        <ul class="submenu megamenu">
                            <li>
                                <ul>
                                    <li><a href="extras-profile.html">Profile</a></li>
                                    <li><a href="extras-sitemap.html">Sitemap</a></li>
                                    <li><a href="extras-about.html">About Us</a></li>
                                    <li><a href="extras-contact.html">Contact</a></li>
                                    <li><a href="extras-members.html">Members</a></li>
                                    <li><a href="extras-timeline.html">Timeline</a></li>
                                    <li><a href="extras-invoice.html">Invoice</a></li>
                                </ul>
                            </li>

                            <li>
                                <ul>
                                    <li><a href="extras-search-result.html">Search Result</a></li>
                                    <li><a href="extras-emailtemplate.html">Email Template</a></li>
                                    <li><a href="extras-maintenance.html">Maintenance</a></li>
                                    <li><a href="extras-coming-soon.html">Coming Soon</a></li>
                                    <li><a href="extras-faq.html">FAQ</a></li>
                                    <li><a href="extras-gallery.html">Gallery</a></li>
                                    <li><a href="extras-pricing.html">Pricing</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>

                    <li class="has-submenu">
                        <a href="#"> <i class="mdi mdi-comment-text"></i>Blog
                        </a>
                        <ul class="submenu">
                            <li><a href="blogs-dashboard.html">Dashboard</a></li>
                            <li><a href="blogs-blog-list.html">Blog List</a></li>
                            <li><a href="blogs-blog-column.html">Blog Column</a></li>
                            <li><a href="blogs-blog-post.html">Blog Post</a></li>
                            <li><a href="blogs-blog-add.html">Add Blog</a></li>
                        </ul>
                    </li>

                    <li class="has-submenu">
                        <a href="#"> <i class="mdi mdi-home-map-marker"></i>Real Estate
                        </a>
                        <ul class="submenu">
                            <li><a href="real-estate-dashboard.html">Dashboard</a></li>
                            <li><a href="real-estate-list.html">Property List</a></li>
                            <li><a href="real-estate-column.html">Property Column</a></li>
                            <li><a href="real-estate-detail.html">Property Detail</a></li>
                            <li><a href="real-estate-agents.html">Agents</a></li>
                            <li><a href="real-estate-profile.html">Agent Details</a></li>
                            <li><a href="real-estate-add.html">Add Property</a></li>
                        </ul>
                    </li>

                    <li class="has-submenu">
                        <a href="#"> <i class="mdi mdi-flip-horizontal"></i>Layouts
                        </a>
                        <ul class="submenu">
                            <li><a href="layouts-vertical.html">Vertical</a></li>
                            <li><a href="layouts-topbar-light.html">Topbar Light</a></li>
                            <li><a href="layouts-center-menu.html">Center Menu</a></li>
                            <li><a href="layouts-normal-header.html">Unsticky Header</a></li>
                        </ul>
                    </li>

                </ul>
                <!-- End navigation menu -->

                <div class="clearfix"></div>
            </div>
            <!-- end #navigation -->
        </div>
        <!-- end container -->
    </div>
    <!-- end navbar-custom -->
</header>