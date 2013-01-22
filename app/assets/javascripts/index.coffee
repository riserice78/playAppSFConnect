
if window.location.pathname.indexOf("index2") > -1
  $ ->
    $(".index .results").prepend $("<span>Results</span>")
    $.get "/accounts", (data) ->
      $.each data, (index, accountModel) ->
        $("#accounts").append $("<li>").text accountModel.name + " of " + accountModel.billingCity + ", " + accountModel.billingState

