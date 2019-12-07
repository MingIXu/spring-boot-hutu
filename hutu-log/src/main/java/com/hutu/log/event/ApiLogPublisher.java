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

package com.hutu.log.event;

import com.hutu.log.annotation.ApiLog;
import com.hutu.log.entity.LogApi;
import com.hutu.security.utils.SpringUtil;
import com.hutu.security.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * API日志信息事件发送
 *
 * @author Chill
 */
public class ApiLogPublisher {

    public static void publishEvent(String methodName, String methodClass, ApiLog apiLog, long time) {
        HttpServletRequest request = WebUtil.getRequest();
        LogApi logApi = new LogApi()
				.setType(apiLog.type().value)
                .setTitle(apiLog.value())
                .setTime(String.valueOf(time))
                .setMethodClass(methodClass)
                .setParams(WebUtil.getRequestParamString(request))
                .setMethodName(methodName);
        SpringUtil.publishEvent(new ApiLogEvent(logApi));
    }

}
