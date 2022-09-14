# 一.前言

这个mod受SimpleLogin和WorldEdit启发，感谢个两位大佬给我的思路。这个mod做的比较仓促，有很多功能没有完善，如果有好的建议或者发现了bug请前往github反馈。

# 二.命令列表

| 命令                | 使用说明                                                    |
| ------------------- | ----------------------------------------------------------- |
| /tpa (player)       | 向player发出传送请求                                        |
| /tpaccept           | 同意传送请求                                                |
| /gm (modename)      | 更改游戏模式为0/survival/1/creative/2/adventure/3/spectator |
| /home               | 返回设置好的家                                              |
| /sethome            | 设置当前位置为家                                            |
| /setwarp (warpname) | 设置坐标点（需要op权限）                                    |
| /delwarp (warpname) | 删除坐标点 （需要op权限）                                   |
| /warp (warpname)    | 传送到指定坐标点                                            |
| /back               | 返回上一次死亡点或者传送点                                  |

# 三.设置文件

设置文件为./mods/sets.txt

目前只有一个设置项为language即语言设置，目前支持的语言为英语（en-us）和简体中文（zh-cn）

# 四.已知问题

1.无法自动填充，输入框会显示为未知命令

# 五.未来计划

1.更丰富的可设置项

2.移植到fabric框架上面（目前fabric框架开发文档的命令页面处于不可用状态）

3.支持繁体中文

4.添加更多的命令

# 六.特别鸣谢

**[WorldEdit](https://github.com/EngineHub/WorldEdit)**

**[SimpleLogin](https://github.com/TheSmileCat/SimpleLogin)**