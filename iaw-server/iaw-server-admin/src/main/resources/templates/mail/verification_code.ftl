<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<div class="app" >

    <div  class="outer"  style="width: 600px;height: 300px">
        <div class="logo">
            <img src="https://project-1258741617.cos.ap-shanghai.myqcloud.com/%E4%BA%91.svg">
                <span>iaw</span>
        </div>

        <div class="soild"style="height: 230px">

                <div class="left-20">
                    <p><b>${title}</b></p>
                </div>
            <hr class="soild"/>

            <div style="height: 130px" class="left-20">
                <p>Hi,${toUser}!</p>
                ${content}
            </div>
        </div>
        <div class="footer" style="height: 50px">
            <p style="color: darkgray">@MayPrayer.top</p>
        </div>


    </div>

</div>
</body>
</html>

<style>

    .app{
        margin: 0px;
        padding: 0px;
    }
    .outer{
        margin: 0 auto;
    }

    .soild{
        border: 1px solid #E3E9ED ;
    }


    .left-20{
        margin-left: 20px;
    }


    .logo{
       text-align: center;
       width: 600px;height: 50px;
    }
    img,span{
        width: 36px;
        height: 36px;
        vertical-align: middle;
    }
    span{
        font-size: 36px;
    }
</style>