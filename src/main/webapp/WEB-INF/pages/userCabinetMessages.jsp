<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<link href="<c:url value="/resources/css/profile.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/messages.css"/>" rel="stylesheet">
<script src="https://use.fontawesome.com/45e03a14ce.js"></script>

<div class="container">
    <div class="container" style="width: 30%; float: left">
        <div class="row row-content" style="padding: 0px">
            <div class="list-group doc-menu">
                <a href="<c:url value="/user/cabinet"/>" class="list-group-item">
                    <spring:message code="messages.profile"/>
                </a>
                <a href="<c:url value="/user/doctors"/>" class="list-group-item">
                    <spring:message code="messages.doctors"/>
                </a>
                <a href="<c:url value="/user/medicalcard"/>" class="list-group-item">
                    <spring:message code="messages.medicalCard"/>
                </a>
                <a href="<c:url value="/user/messages"/>" class="navbar-inverse list-group-item">
                    <spring:message code="messages.messages"/>
                </a>
            </div>
        </div>


        <div class="chat_container">
            <div class="row row-content" style="padding: 0px">
                <div id="custom-search-input">
                    <div class="input-group">
                        <input type="text" class="  search-query form-control"
                               placeholder="Conversation"/>
                        <button class="btn btn-danger" type="button">
                            <span class=" glyphicon glyphicon-search"></span>
                        </button>
                    </div>
                </div>
                <div class="dropdown all_conversation">
                    <button class="dropdown-toggle" type="button" id="dropdownMenu2"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-weixin" aria-hidden="true"></i>
                        All Conversations
                        <span class="caret pull-right"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                        <li><a href="#"> All Conversation </a>
                            <ul class="sub_menu_ list-unstyled">
                                <li><a href="#"> All Conversation </a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li><a href="#">Separated link</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li><a href="#">Separated link</a></li>
                    </ul>
                </div>
                <div class="member_list">
                    <ul class="list-unstyled">
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong
                                        class="pull-right">
                                    09:45AM</strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong> <span
                                        class="badge pull-right">3</span>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong
                                        class="pull-right">
                                    09:45AM</strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong> <span
                                        class="badge pull-right">3</span>
                                </div>
                            </div>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="container" style="width: 70%; float: right">
        <div class="row row-content" style="padding: 0px">
            <div class="main_section">
                <div class=" message_section">
                    <div class="row">
                        <div class="new_message_head">
                            <div class="pull-left">
                                <button><i class="fa fa-plus-square-o" aria-hidden="true"></i> New Message
                                </button>
                            </div>
                            <div class="pull-right">
                                <div class="dropdown">
                                    <button class="dropdown-toggle" type="button" id="dropdownMenu1"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fa fa-cogs" aria-hidden="true"></i> Setting
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-right"
                                        aria-labelledby="dropdownMenu1">
                                        <li><a href="#">Action</a></li>
                                        <li><a href="#">Profile</a></li>
                                        <li><a href="#">Logout</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div><!--new_message_head-->

                        <div class="chat_area" style="background: white">
                            <ul class="list-unstyled">
                                <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg"
                          alt="User Avatar" class="img-circle">
                     </span>
                                    <div class="chat-body1 clearfix">
                                        <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It
                                            has roots in a piece of classical Latin literature from 45 BC,
                                            making it over 2000 years old. Richard McClintock, a Latin professor
                                            at Hampden-Sydney College in Virginia.</p>
                                        <div class="chat_time pull-right">09:40PM</div>
                                    </div>
                                </li>
                                <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg"
                          alt="User Avatar" class="img-circle">
                     </span>
                                    <div class="chat-body1 clearfix">
                                        <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It
                                            has roots in a piece of classical Latin literature from 45 BC,
                                            making it over 2000 years old. Richard McClintock, a Latin professor
                                            at Hampden-Sydney College in Virginia.</p>
                                        <div class="chat_time pull-right">09:40PM</div>
                                    </div>
                                </li>
                                <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg"
                          alt="User Avatar" class="img-circle">
                     </span>
                                    <div class="chat-body1 clearfix">
                                        <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It
                                            has roots in a piece of classical Latin literature from 45 BC,
                                            making it over 2000 years old. Richard McClintock, a Latin professor
                                            at Hampden-Sydney College in Virginia.</p>
                                        <div class="chat_time pull-right">09:40PM</div>
                                    </div>
                                </li>
                                <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg"
                          alt="User Avatar" class="img-circle">
                     </span>
                                    <div class="chat-body1 clearfix">
                                        <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It
                                            has roots in a piece of classical Latin literature from 45 BC,
                                            making it over 2000 years old. Richard McClintock, a Latin professor
                                            at Hampden-Sydney College in Virginia.</p>
                                        <div class="chat_time pull-right">09:40PM</div>
                                    </div>
                                </li>
                                <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg"
                          alt="User Avatar" class="img-circle">
                     </span>
                                    <div class="chat-body1 clearfix">
                                        <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It
                                            has roots in a piece of classical Latin literature from 45 BC,
                                            making it over 2000 years old. Richard McClintock, a Latin professor
                                            at Hampden-Sydney College in Virginia.</p>
                                        <div class="chat_time pull-right">09:40PM</div>
                                    </div>
                                </li>
                                <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg"
                          alt="User Avatar" class="img-circle">
                     </span>
                                    <div class="chat-body1 clearfix">
                                        <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It
                                            has roots in a piece of classical Latin literature from 45 BC,
                                            making it over 2000 years old. Richard McClintock, a Latin professor
                                            at Hampden-Sydney College in Virginia.</p>
                                        <div class="chat_time pull-right">09:40PM</div>
                                    </div>
                                </li>
                            </ul>
                        </div><!--chat_area-->
                        <div class="message_write">
                            <textarea class="form-control" placeholder="type a message"></textarea>
                            <div class="clearfix"></div>
                            <div class="chat_bottom"><a href="#" class="pull-left upload_btn"><i
                                    class="fa fa-cloud-upload" aria-hidden="true"></i>
                                Add Files</a>
                                <a href="#" class="pull-right btn btn-success">
                                    Send</a></div>
                        </div>
                    </div>
                </div> <!--message_section-->
            </div>
        </div>
    </div>
</div>


