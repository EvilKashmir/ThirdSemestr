<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.11.2020
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Main</title>
  <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>
<%--<h5 class="card-title">Card title</h5>
<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
<a href="#" class="btn btn-primary">Go somewhere</a>--%>
<div class="container">
  <section class="mt-5 mb-4">
    <div class="row">
      <div class="col"></div>
      <div class="col-md-5 align-self-center">
        <div class="card wish-list mb-4">
          <div class="card">
            <div class="card-body">
              <%
                String error = (String) request.getAttribute("error");
                String success = (String) request.getAttribute("success");
                if (error != null) {
              %>
              <div class="alert alert-danger fade show" role="alert">
                <%=error%>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <%
              } else if (success != null) {
              %>
              <div class="alert alert-success fade show" role="alert">
                <%=success%>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <%
                }
              %>
              <div class="card">
                <img class="card-img-top" src="/captcha" alt="Captha">
                <div class="card-body">
                  <form autocomplete="off" action="/main" method="POST" id="form">
                    <div class="form-group">
                      <span class="text-black-50">Captcha</span>
                      <input id="captcha-text" type="text" class="form-control" name="captcha"
                             required>
                    </div>
                    <button id='submit'
                            class="btn btn-primary btn-lg btn-block waves-effect waves-light"
                            type="submit">
                      Send
                    </button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col"></div>
    </div>
  </section>
</div>
</div>
</div>
</body>
</html>
