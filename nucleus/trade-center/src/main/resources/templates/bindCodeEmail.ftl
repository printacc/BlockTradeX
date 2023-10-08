<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>TRADE 邮箱验证</title>
    <style>
        /* Reset styles for common tags */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* Global styles */
        body {
            margin: 0;
            padding: 0;
            font-family: Arial;
            font-size: 16px;
            color: #444;
            background-color: #f9f9f9;
        }

        .container {
            margin: 0;
            padding: 20px;
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        .card {
            margin: 0;
            padding: 0;
            width: 90%;
            max-width: 600px;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
            overflow: hidden;
        }

        .card-header {
            margin: 0;
            padding: 20px;
            background-color: #3f51b5;
            color: #fff;
            text-align: center;
        }

        .card-header h1 {
            margin: 0;
            padding: 0;
            font-size: 28px;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 2px;
        }

        .card-body {
            margin: 0;
            padding: 40px;
            text-align: center;
        }

        .code {
            margin: 20px 0;
            padding: 20px;
            font-size: 42px;
            font-weight: bold;
            letter-spacing: 10px;
            color: #3f51b5;
            background-color: #f9f9f9;
            border: 2px solid #3f51b5;
            border-radius: 5px;
            transition: all 0.3s ease-in-out;
        }

        .code:hover {
            background-color: #3f51b5;
            color: #fff;
        }

        .btn {
            color: #fff;
            background-color: #3f51b5;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            text-transform: uppercase;
            text-decoration: none;
            cursor: pointer;
            transition: all 0.3s ease-in-out;
        }

        .btn:hover {
            background-color: #2196f3;
        }

        .footer {
            margin-top: 40px;
            font-size: 12px;
            color: #999;
            text-align: center;
        }

        .footer a {
            color: #999;
            text-decoration: none;
        }

        .footer a:hover {
            color: #3f51b5;
        }

        /* Media queries */
        @media screen and (max-width: 600px) {
            .card {
                margin-top: 40px;
            }

            .card-body {
                padding: 20px;
                text-align: center;
            }

            .code {
                font-size: 29px;
                letter-spacing: 9px;
                margin: 20px auto;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="card">
        <div class="card-header">
            <h1>TRADE 邮箱验证</h1>
        </div>
        <div class="card-body">
            <p>
                您好，<br />
                感谢您选择 TRADE。以下是您的验证码，请在 5 分钟内进行验证：
            </p>
            <div class="code">${code}</div>
            <p>如果您未进行验证操作，请忽略此邮件。</p>
        </div>
    </div>
    <div class="footer">
        <p>此邮件由 TRADE 发送，请勿回复。</p>
        <p>如果您有任何问题，请联系我们的客服：<a href="#">support@trade.com</a></p>
    </div>
</div>
</body>
</html>