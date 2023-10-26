import os
import mitmproxy.http
from mitmproxy import ctx

def response(flow:mitmproxy.http.HTTPFlow):
    #选择本地文件路径
    path='E:/dy_project/dy_data_mitmdump'
    try:
        os.mkdir(path)
    except:
        pass
    aim_url='https://aweme-eagle.snssdk.com/aweme/v1/user'
    #捕捉所有目标url开头的url
    if flow.request.url.startswith(aim_url):
        #将响应体赋值到json_data中
        json_data=flow.response.content
        #定义文件名：‘dy_user_data_’+时间戳
        filename='dy_u_data'+flow.request.url.split('&ts=')[-1].split('&as=')[0]
        with open(path+'/'+filename+'.json','wb')as f:
            #以二进制的格式将json文件保存到本地
            f.write(json_data)
            ctx.log.info(f'**********{filename}.json下载了**********')