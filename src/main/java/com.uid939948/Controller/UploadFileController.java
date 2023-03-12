package com.uid939948.Controller;

import com.uid939948.Result.Result;
import com.uid939948.Result.ResultCode;
import com.uid939948.Service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UploadFileController {
    @Autowired
    @Lazy
    private SetService checkService;

    @ResponseBody
    @GetMapping(value = "/getConfigUid")
    public Result getConfigUid() {
        return new Result(ResultCode.SUCCESS, checkService.getConfigUid());
    }

    @ResponseBody
    @PostMapping(value = "/sendConfigUid")
    public Result sendConfigUid(@RequestHeader(value = "uid") String uid) {
        Result result = new Result(ResultCode.SUCCESS, checkService.sendConfigUid(uid));
        return result;
    }

    @ResponseBody
    @PostMapping(value = "/saveConfigUid")
    public ResponseEntity<Boolean> saveConfigUid(String uid) {
        return new ResponseEntity<>(checkService.saveConfigUid(uid), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/exit")
    public ResponseEntity<String> exit() {
        System.exit(0);
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}
