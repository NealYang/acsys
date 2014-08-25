<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>New Bill</title>

    <!-- Bootstrap -->
    <link href="/includes/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/custom.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="/includes/js/html5shiv.min.js"></script>
    <script src="/includes/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Accounting System</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="home">Home</a></li>
                <li><a href="grouping">Manage Grouping</a></li>
                <li><a href="account/logout">Log Out</a></li>
            </ul>
            <div class="navbar-form navbar-right">
                <button id="new-bill-btn" class="form-control btn btn-default">New Bill</button>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="input-group">
                <span class="input-group-addon">Grouping</span>
                <select class="form-control" name="grouping">
                    <option value="All">All</option>
                    <option value="A"> A</option>
                    <option value="B"> B</option>
                    <option value="C"> C</option>
                </select>
            </div>
            <table class="table">
                <thead>
                    <tr>
                        <th>User<span class="caret"></span></th>
                        <th>Amount<span class="caret"></span></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="">
                        <td>Michael</td>
                        <td>68.9</td>
                    </tr>
                    <tr class="">
                        <td>Frank</td>
                        <td>51.1</td>
                    </tr>
                    <tr class="">
                        <td>Norbert</td>
                        <td>50.8</td>
                    </tr>
                    <tr class="">
                        <td>Wisdom</td>
                        <td>17.5</td>
                    </tr>
                    <tr class="">
                        <td>Quinn</td>
                        <td>12.5</td>
                    </tr>
                    <tr class="">
                        <td>Rony</td>
                        <td>2.4</td>
                    </tr>
                    <tr class="">
                        <td>Ray</td>
                        <td>-6.0</td>
                    </tr>
                    <tr class="">
                        <td>Bob</td>
                        <td>-6.2</td>
                    </tr>
                    <tr class="">
                        <td>Ben</td>
                        <td>-10.6</td>
                    </tr>
                    <tr class="">
                        <td>Harry</td>
                        <td>-12.2</td>
                    </tr>
                    <tr class="">
                        <td>Yee</td>
                        <td>-12.5</td>
                    </tr>
                    <tr class="">
                        <td>Mandy</td>
                        <td>-15.0</td>
                    </tr>
                    <tr class="">
                        <td>Johnson</td>
                        <td>-15.0</td>
                    </tr>
                    <tr class="">
                        <td>Andy</td>
                        <td>-16.1</td>
                    </tr>
                    <tr class="">
                        <td>Jackie</td>
                        <td>-16.6</td>
                    </tr>
                    <tr class="">
                        <td>Claude</td>
                        <td>-18.7</td>
                    </tr>
                    <tr class="">
                        <td>Zeke</td>
                        <td>-21.9</td>
                    </tr>
                    <tr class="">
                        <td>Jeffrey</td>
                        <td>-21.9</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="col-sm-9 col-md-10 col-sm-offset-3 col-md-offset-2 main">
            <h2 class="sub-header">New Bill</h2>
            <form role="form">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="date">Date</label>
                            <input type="date" class="form-control" id="date">
                        </div>
                        <div class="form-group">
                            <label for="payer">Payer</label>
                            <select id="payer" class="form-control" name="payer">
                                <option value="Jett">Jett</option>
                                <option value="Harry">Harry</option>
                                <option value="Frank">Frank</option>
                                <option value="Bob">Bob</option>
                                <option value="Wisdom">Wisdom</option>
                                <option value="Ray">Ray</option>
                                <option value="Fred">Fred</option>
                                <option value="Harvey">Harvey</option>
                                <option value="Yee">Yee</option>
                                <option value="Valence">Valence</option>
                                <option value="Michael">Michael</option>
                                <option value="Claude">Claude</option>
                                <option value="Jackie">Jackie</option>
                                <option value="Quinn">Quinn</option>
                                <option value="Swing">Swing</option>
                                <option value="Jeffrey">Jeffrey</option>
                                <option value="Ben">Ben</option>
                                <option value="Rony">Rony</option>
                                <option value="Zeke">Zeke</option>
                                <option value="Andy">Andy</option>
                                <option value="Johnson">Johnson</option>
                                <option value="Mandy">Mandy</option>
                                <option value="Norbert">Norbert</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="grouping">Grouping</label>
                            <select id="grouping" class="form-control" name="grouping">
                                <option value="">A</option>
                                <option value="">B</option>
                                <option value="">C</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="amount">Amount</label>
                            <input type="text" class="form-control" id="amount">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="place">Place</label>
                            <input type="text" class="form-control" id="place">
                        </div>
                        <div class="form-group radio-group">
                            <label class="radio-inline">
                                <input type="radio" checked name="inlineRadioOptions" id="ave-pay" value="The Average Pay">Average Pay
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions" id="go-dutch" value="Go Dutch"> Go Dutch
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="attendant">Attendant</label>
                    <select id="attendant" name="attendant" multiple class="form-control">
                        <option value="Jett">Jett</option>
                        <option value="Harry">Harry</option>
                        <option value="Frank">Frank</option>
                        <option value="Bob">Bob</option>
                        <option value="Wisdom">Wisdom</option>
                        <option value="Ray">Ray</option>
                        <option value="Fred">Fred</option>
                        <option value="Harvey">Harvey</option>
                        <option value="Yee">Yee</option>
                        <option value="Valence">Valence</option>
                        <option value="Michael">Michael</option>
                        <option value="Claude">Claude</option>
                        <option value="Jackie">Jackie</option>
                        <option value="Quinn">Quinn</option>
                        <option value="Swing">Swing</option>
                        <option value="Jeffrey">Jeffrey</option>
                        <option value="Ben">Ben</option>
                        <option value="Rony">Rony</option>
                        <option value="Zeke">Zeke</option>
                        <option value="Andy">Andy</option>
                        <option value="Johnson">Johnson</option>
                        <option value="Mandy">Mandy</option>
                        <option value="Norbert">Norbert</option>
                    </select>
                </div>
                <div class="row go-dutch-wrap hide">
                    <div class="col-md-2">
                        <div class="form-group">
                            <label for="aaa">aaa</label>
                            <input id="aaa" class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label for="bbb">bbb</label>
                            <input id="bbb" class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label for="ccc">ccc</label>
                            <input id="ccc" class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label for="ddd">ddd</label>
                            <input id="ddd" class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label for="eee">eee</label>
                            <input id="eee" class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label for="fff">fff</label>
                            <input id="fff" class="form-control" type="text"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="comment">Comment</label>
                    <textarea id="comment" class="form-control" rows="2"></textarea>
                </div>
                <div class="btn-group2">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/includes/js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/includes/js/bootstrap.min.js"></script>
<script type="text/javascript">
    (function($){
        $("#go-dutch").click(function(){
            $("#attendant").change(function(){
                $(".go-dutch-wrap").removeClass("hide");
            })
        });
        $("#ave-pay").click(function(){
            if(!$(".go-dutch-wrap").hasClass("hide")){
                $(".go-dutch-wrap").addClass("hide");
            }
        });
        $("#new-bill-btn").click(function(){
            window.location.href="bill"
        })
    })(jQuery);
</script>
</body>
</html>