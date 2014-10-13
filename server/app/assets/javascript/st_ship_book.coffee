$(document).ready ->
  fromShip = new Vue
    el: '#from_name'

    data:
      sCounts: []
      dropCounts: []

    methods:
      getSCounts: (sid) ->
        $.getJSON "/rest/v1/recipe/from_ship/#{sid}", {}, (ret) =>
          @sCounts = ret
        $.getJSON "/rest/v1/drop_from_ship/#{sid}", {}, (ret) =>
          @dropCounts = ret

    created: ->
      sid = $(@$el).attr("data-ship-id")
      @getSCounts(sid)
