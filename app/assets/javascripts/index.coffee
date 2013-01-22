$ ->
  $.get "/accounts", (data) ->
    $.each data, (index, accountModel) ->
      $("#accounts").append $("<li>").text accountModel.name + " of " + accountModel.billingCity + ", " + accountModel.billingState
