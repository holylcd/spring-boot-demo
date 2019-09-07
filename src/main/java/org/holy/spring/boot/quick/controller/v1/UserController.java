package org.holy.spring.boot.quick.controller.v1;

import com.github.pagehelper.PageHelper;
import org.holy.spring.boot.quick.common.exception.BizException;
import org.holy.spring.boot.quick.common.http.rest.request.body.CommonPage;
import org.holy.spring.boot.quick.common.http.rest.response.body.ResponseBodyPage;
import org.holy.spring.boot.quick.constants.biz.CommonBizStatus;
import org.holy.spring.boot.quick.domain.UserInfo;
import org.holy.spring.boot.quick.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户控制器
 * @author holy
 * @date 2019/9/2 15:46
 * @version 1.0.0
 */
@RestController
@RequestMapping(
        value = "user",
        produces = "application/vnd.quick.app-v1.0.1+json"
)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 普通查询 form / url
     * @param commonPage
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseBodyPage<UserInfo> form(@Valid CommonPage commonPage) {
        PageHelper.startPage(commonPage.getPage(), commonPage.getPrePage());
        List<UserInfo> userInfos = userService.findAll();

        return ResponseBodyPage
                .ok(userInfos, commonPage.getPrePage());
    }

    /**
     * 普通查询 form / url，需要权限
     * @param commonPage
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public ResponseBodyPage<UserInfo> formAdmin(@Valid CommonPage commonPage) {
        PageHelper.startPage(commonPage.getPage(), commonPage.getPrePage());
        List<UserInfo> userInfos = userService.findAll();

        return ResponseBodyPage
                .ok(userInfos, commonPage.getPrePage());
    }

    /**
     * 普通查询 json
     * @param commonPage
     * @return
     */
    @RequestMapping(value = "/body", method = RequestMethod.GET)
    public ResponseBodyPage<UserInfo> requestBody(@Valid @RequestBody CommonPage commonPage) {
        PageHelper.startPage(commonPage.getPage(), commonPage.getPrePage());
        List<UserInfo> userInfos = userService.findAll();

        return ResponseBodyPage
                .ok(userInfos, commonPage.getPrePage());
    }

    /**
     * 复杂查询
     * @return
     */
    @RequestMapping(value = "order", method = RequestMethod.GET)
    public ResponseBodyPage<UserInfo> order() {
        int pageNum = 2, pageSize = 8;
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UserInfo.class);
        example.setOrderByClause("realname");
        List<UserInfo> userInfos = userService.findAll1(example);

        return ResponseBodyPage
                .ok(userInfos, pageSize);
    }

    /**
     * 异常测试
     * @return
     */
    @RequestMapping(value = "exception", method = RequestMethod.GET)
    public ResponseBodyPage<UserInfo> exception() {
        //throw new BizException();
        throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, CommonBizStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * http status test
     * @param status
     * @return
     */
    @RequestMapping(value = "status", method = RequestMethod.GET)
    public ResponseEntity status(@RequestParam(value = "status") Integer status) {
        return ResponseEntity
                .status(status)
                .body("faild");
    }

}
