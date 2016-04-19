# -*- coding: utf-8 -*-
import urllib.request
import re 

def canshu(str1):
    page = getHtml(str1)
    page = str(page)
    u1 = re.compile(r"<span id=\"newPmVal(.*?)</span>")
    u2 = re.compile(r"<span id=\"newPm(.*?)/a")
    u3 = re.compile(r"<a(.*?)<")
    u4 = re.compile(r">(.*?)<")
    p2 = re.compile(r"<span>(.*?)<") 
    p3 = re.compile(r"屏幕尺寸(.*?)<")
    p4 = re.compile(r"屏幕分辨率(.*?)<") 
    p5 = re.compile(r"屏幕(.*?)<")
    p6 = re.compile(r"分辨率(.*?)<")
    p7 = re.compile(r"CPU(.*?)<")
    p8 = re.compile(r"核心(.*?)<")
    p9 = re.compile(r"电池(.*?)<")
    p10 = re.compile(r"续航(.*?)<")
    p11 = re.compile(r"笔记(.*?)<")
    p12 = re.compile(r"厚度(.*?)<")
    p13 = re.compile(r"显卡(.*?)<")
    p14 = re.compile(r"显存(.*?)<")
    t1 = re.compile(r"title=\"(.*?)\"")
    p1 = re.compile(r"title=\"(.*?)/p")
    f = open("1.js", "a")
    flag = 0
    flag1 =0
    for i in range(1,70):
      u5 = re.compile(r"<span id=\"newPmVal_" + str(i) + "(.*?)</span>")
      u6 = re.compile(r"<span id=\"newPmName_" + str(i) + "(.*?)</span>")
      s1 = re.compile(r">(.*?)<")
      s2 = re.compile(r"<a(.*?)<")
      if(u5.search(page) != None):
        if(s2.search(u5.search(page).group()) != None):
          d = "[" + str(k) + "]"+"[CHA]" + "[" + str(i) + "]" + s1.search(u6.search(page).group()).group() + "=" + s1.search(s2.search(u5.search(page).group()).group()).group() + "\n"
        else:
          d = "[" + str(k) + "]"+"[CHA]" + "[" + str(i) + "]" + s1.search(u6.search(page).group()).group() + "=" + s1.search(u5.search(page).group()).group() + "\n"
        d = d.replace('<','')
        d = d.replace('>','')
        d = d.replace('html\'','')
        f.write(d)


    

def getHtml(url):
    print(url)
    page = urllib.request.urlopen(url)
    html = page.read().decode('GBK', 'ignore')
    return html

def paqu(str1):
  global k
  f = open("1.js", "a")
  if (str1 != "/notebook.html" and str1 != "/notebook_Ultrabook.html"):
    url = "http://detail.zol.com.cn" + str1
    page = getHtml(url)
    page = str(page)
    p2 = re.compile(r"img itemprop=\"photo\" src=\"(.*?.jpg)") 
    p3 = re.compile(r"http(.*?.jpg)")
    p4 = re.compile(r"h1(.*?)</h1>")
    p5 = re.compile(r">(.*?)<")
    p6 = re.compile(r"(<a href=)(.*?)参数")
    p7 = re.compile(r"/(.*?.shtml)")
    p8 = re.compile(r"\]\,\[(.*?)\,")  
    if(p8.search(page) != None): 
        d = p8.search(page).group()  
        d = d.replace('[','')
        d = d.replace(']','')
        d = d.replace(',','')
        f.write(  "[" + str(k) + "]" + "[PRC]" + "=" + "\""  + d + "\"" + ";" + "\n")
    for i in p2.finditer(page):
      if (i != None):
        match = p3.search(i.group())  
        f.write(  "[" + str(k) + "]" + "[PIC]" + "=" + "\""  + match.group().replace('<','') + "\"" + ";" + "\n")
    for j in p4.finditer(page):
      if (j != None):
        match1 = p5.search(j.group())
        f.write(  "[" + str(k) + "]" + "[Name]" + "=" + "\""  + match1.group().replace('<','') + "\"" + ";" + "\n")
    match2 = p6.search(page)
    if ( match2 !=  None): 
        match2 = p7.search(match2.group()) 
        url1 = "http://detail.zol.com.cn" + match2.group()
        canshu(url1)
 

def A(url):
  global k
  html = str(getHtml(url))
  i1 = html.find("<div class=\"pic-mode-box\">")
  i2 = html.rfind("<div class=\"adSpace\" id=\"list_list_prolist_26\"")
  i3 = html[i1:i2+7]
  i3=i3.replace(" ","")
  temp = 0
  p1 = re.compile(r"((/notebook|/ultrabook).*?.(shtml|html))")
  p2 = re.compile(r"((/notebook|/ultrabook).*?.shtml)")   
  for i in p1.finditer(i3):  
    for j in p2.finditer(i.group()):
      if (j.group() != temp): 
        if (j.group() != 0): 
          paqu(j.group())
          k += 1
          print(k)
        else: 
          k += 1  
      temp = j.group()
  

k=1
for i in range(1,111):
  url = "http://detail.zol.com.cn/notebook_index/subcate16_0_list_1_0_1_2_0_"+str(i)+".html"
  A(url)
input()






  