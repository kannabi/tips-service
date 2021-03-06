package com.droptable.tipsservice.tips;


import com.droptable.tipsservice.dao.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tips")
public class TipsController {

    private final TipsService tipsService;

    @Autowired
    public TipsController(TipsService tipsService) {
        this.tipsService = tipsService;
    }

    @RequestMapping(value = {"/tip_pay"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("Process tips for waiter")
    public Response processTip(
            @ApiParam("Waiter's id")
                    @RequestParam String waiterId,
            @ApiParam("Sum of a tip to the waiter")
                    @RequestParam BigDecimal sum,
            @ApiParam("Time of the tips transaction")
                    @RequestParam Long time
    ) {
        try {
            tipsService.registerTips(waiterId, sum, time);
            return new Response(HttpStatus.OK.value(), "Tip has been processed");
        } catch (NoSuchElementException e) {
            return new Response(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }
    }
}
