$(document).ready(function()
{
    if($("#vector_world_map").length > 0)
    {
        //
        //  Static Data
        //
        var gdpData =
        {
            "AF": 1,
            "AL": 1,
            "DZ": 1,
            "AO": 4,
            "AG": 4,
            "AR": 5,
            "AU": 12,
            "BH": 11,
            "BD": 8,
            "US": 15
        };
        
        // Need To Create A Function Here To Do A JSON Request
        // Then Use That Data To Supply The Map
        
        
        //
        //  Render The Global Map
        //
        $('#vector_world_map').vectorMap(
        {
            map: 'world_mill_en',
            series:
            {
                regions: [{
                    values: gdpData,
                    scale: ['#C8EEFF', '#0071A4'],
                    normalizeFunction: 'polynomial',
                    legend: {
                        vertical: true
                    },
                }]
            },
            onRegionTipShow: function(event, label, code)
            {
                label.html('<b>'+label.html()+'</b></br>' + '<b>Count: </b>' + gdpData[code]);
            },
        });
    }
});