/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hutu.log.config;

import com.hutu.log.aspect.ApiLogAspect;
import com.hutu.log.event.ApiLogListener;
import com.hutu.log.service.ApiLogService;
import com.hutu.log.service.ApiLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 日志工具自动配置
 *
 * @author Chill
 */
@Order
@Configuration
@ConditionalOnWebApplication
public class LogAutoConfiguration {

    @Autowired(required = false)
    private ApiLogService apiLogService;

    @Bean
    public ApiLogAspect apiLogAspect() {
        return new ApiLogAspect();
    }

    @Bean
    public ApiLogListener sysLogListener() {
        return new ApiLogListener(apiLogService==null?new ApiLogServiceImpl():apiLogService);
    }
}
