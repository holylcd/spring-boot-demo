package org.holy.spring.boot.quick.controller.v1;

import com.github.pagehelper.PageHelper;
import org.holy.spring.boot.quick.bean.domain.UserDO;
import org.holy.spring.boot.quick.bean.model.user.UserInfoVO;
import org.holy.spring.boot.quick.bean.model.user.TokenVO;
import org.holy.spring.boot.quick.bean.model.user.UserVO;
import org.holy.spring.boot.quick.common.http.rest.request.body.CommonPage;
import org.holy.spring.boot.quick.common.http.rest.response.body.ResponseBody;
import org.holy.spring.boot.quick.common.http.rest.response.body.ResponseBodyData;
import org.holy.spring.boot.quick.common.http.rest.response.body.ResponseBodyPage;
import org.holy.spring.boot.quick.component.security.UserPrincipal;
import org.holy.spring.boot.quick.constants.rest.Version;
import org.holy.spring.boot.quick.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        produces = Version.V_1_0_0
)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param userVO
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseBodyData<TokenVO> login(@Valid UserVO userVO) {
        TokenVO tokenVO = userService.login(userVO);
        return ResponseBodyData.ok(tokenVO);
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ResponseBody logout(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        userService.logout(userPrincipal);
        return ResponseBody.ok();
    }

    /**
     * 个人信息
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ResponseBodyData<UserInfoVO> userInfo(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        UserInfoVO info = userService.info(userPrincipal);
        return ResponseBodyData.ok(info);
    }

    /**
     * 普通查询 form / url，需要权限
     * @param commonPage
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public ResponseBodyPage<UserVO> formAdmin(@Valid CommonPage commonPage) {
        PageHelper.startPage(commonPage.getPage(), commonPage.getPrePage());
        List<UserVO> userInfos = userService.findAll();

        return ResponseBodyPage
                .ok(userInfos, commonPage.getPrePage());
    }

    /**
     * 复杂查询
     * @return
     */
    @RequestMapping(value = "order", method = RequestMethod.GET)
    public ResponseBodyPage<UserVO> order() {
        int pageNum = 2, pageSize = 8;
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UserDO.class);
        example.setOrderByClause("realname");
        List<UserVO> userInfos = userService.findAll1(example);

        return ResponseBodyPage
                .ok(userInfos, pageSize);
    }

}
