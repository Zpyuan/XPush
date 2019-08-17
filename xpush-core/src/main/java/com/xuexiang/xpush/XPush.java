/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.xpush;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.xuexiang.xpush.core.IPushClient;
import com.xuexiang.xpush.core.IPushInitCallback;
import com.xuexiang.xpush.core.repeater.IPushDispatcher;
import com.xuexiang.xpush.core.annotation.CommandType;
import com.xuexiang.xpush.core.annotation.ConnectStatus;
import com.xuexiang.xpush.core.annotation.ResultCode;
import com.xuexiang.xpush.entity.XPushMsg;
import com.xuexiang.xpush.logs.ILogger;
import com.xuexiang.xpush.logs.PushLog;

import java.util.Map;

/**
 * XPush推送
 *
 * @author xuexiang
 * @since 2019-08-15 10:48
 */
public final class XPush {

    private XPush() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    //===============================初始化========================================//

    /**
     * 初始化[自动注册]
     *
     * @param application
     * @param registerCallback 注册回调
     */
    public static void init(@NonNull Application application, @NonNull IPushInitCallback registerCallback) {
        _XPush.get().init(application, registerCallback);
    }

    /**
     * 初始化[手动注册]
     *
     * @param application
     * @param pushClient  推送客户端
     */
    public static void init(@NonNull Application application, @NonNull IPushClient pushClient) {
        _XPush.get().init(application, pushClient);
    }

    public static Context getContext() {
        return _XPush.get().getContext();
    }

    //===============================日志========================================//

    /**
     * 设置是否打开调试
     *
     * @param isDebug
     */
    public static void debug(boolean isDebug) {
        PushLog.debug(isDebug);
    }

    /**
     * 设置是否打开调试
     *
     * @param logger
     */
    public static void setLogger(@NonNull ILogger logger) {
        PushLog.setLogger(logger);
    }

    //===============================操作========================================//

    /**
     * 注册
     */
    public static void register() {
        _XPush.get().register();
    }

    /**
     * 注销
     */
    public static void unRegister() {
        _XPush.get().unRegister();
    }

    /**
     * 绑定别名
     *
     * @param alias 别名
     */
    public static void bindAlias(String alias) {
        _XPush.get().bindAlias(alias);
    }

    /**
     * 解绑别名
     *
     * @param alias 别名
     */
    public static void unBindAlias(String alias) {
        _XPush.get().unBindAlias(alias);
    }

    /**
     * 添加标签
     *
     * @param tag 标签
     */
    public static void addTag(String tag) {
        _XPush.get().addTag(tag);
    }

    /**
     * 删除标签
     *
     * @param tag 标签
     */
    public static void deleteTag(String tag) {
        _XPush.get().deleteTag(tag);
    }

    /**
     * @return 推送平台码
     */
    public static int getPlatformCode() {
        return _XPush.get().getPlatformCode();
    }

    /**
     * @return 推送平台的名称
     */
    public static String getPlatformName() {
        return _XPush.get().getPlatformName();
    }

    //===============================IPushDispatcher========================================//

    /**
     * 设置消息推送的事件转发器
     *
     * @param iPushDispatcher 消息推送的事件转发器
     * @return
     */
    public static void setIPushDispatcher(@NonNull IPushDispatcher iPushDispatcher) {
        _XPush.get().setIPushDispatcher(iPushDispatcher);
    }

    /**
     * 转发命令执行结果
     *
     * @param context
     * @param commandType 命令类型
     * @param resultCode  结果码
     * @param error       错误信息
     * @param token       内容
     * @param extraMsg    额外信息
     * @see CommandType#TYPE_ADD_TAG
     * @see CommandType#TYPE_DEL_TAG
     * @see CommandType#TYPE_AND_OR_DEL_TAG
     * @see CommandType#TYPE_REGISTER
     * @see CommandType#TYPE_UNREGISTER
     * @see CommandType#TYPE_BIND_ALIAS
     * @see CommandType#TYPE_UNBIND_ALIAS
     * @see ResultCode#RESULT_ERROR
     * @see ResultCode#RESULT_OK
     */
    public static void transmitCommandResult(Context context, @CommandType int commandType, @ResultCode int resultCode, String error, String token, String extraMsg) {
        _XPush.get().transmitCommandResult(context, commandType, resultCode, error, token, extraMsg);
    }

    /**
     * 转发连接状态发生改变
     *
     * @param context
     * @param connectStatus 推送连接状态
     */
    public static void transmitConnectStatusChanged(Context context, @ConnectStatus int connectStatus) {
        _XPush.get().transmitConnectStatusChanged(context, connectStatus);
    }

    /**
     * 转发通知信息
     *
     * @param context
     * @param notifyId 通知ID
     * @param title    通知标题
     * @param content  通知内容
     * @param extraMsg 额外消息
     */
    public static void transmitNotification(Context context, int notifyId, String title, String content, String extraMsg, Map<String, String> keyValue) {
        _XPush.get().transmitNotification(context, notifyId, title, content, extraMsg, keyValue);
    }

    /**
     * 转发通知点击事件
     *
     * @param context
     * @param notifyId 通知ID
     * @param title    通知标题
     * @param content  通知内容
     * @param extraMsg 额外消息
     */
    public static void transmitNotificationClick(Context context, int notifyId, String title, String content, String extraMsg, Map<String, String> keyValue) {
        _XPush.get().transmitNotificationClick(context, notifyId, title, content, extraMsg, keyValue);
    }

    /**
     * 转发自定义消息
     *
     * @param context
     * @param msg      自定义消息内容
     * @param extraMsg 拓展消息
     */
    public static void transmitMessage(Context context, String msg, String extraMsg, Map<String, String> keyValue) {
        _XPush.get().transmitMessage(context, msg, extraMsg, keyValue);
    }

    /**
     * 转发自定义消息
     *
     * @param context
     * @param pushMsg 推送消息
     */
    public static void transmitMessage(Context context, XPushMsg pushMsg) {
        _XPush.get().transmitMessage(context, pushMsg);
    }


}
