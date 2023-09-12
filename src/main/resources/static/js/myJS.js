/* User Operation */
function signIn()
{
    resetStyle('signInForm');
    var isFromLegal = checkFrom('signInForm');
    if(isFromLegal == 'YES')
    {
        sendForm('/api/signIn', 'POST', getFormData('signInForm'), 'Sign In', '/login');
    }
    else
    {
        document.getElementById('errorMessage').innerHTML = '<div class="alert alert-danger">'+isFromLegal+'</div>';
    }
}
function login()
{
    resetStyle('loginForm');
    var isFromLegal = checkFrom('loginForm');
    if(isFromLegal == 'YES')
    {
        sendForm('/api/login', 'POST', getFormData('loginForm'), 'Log In', '/home');
    }
    else
    {
        document.getElementById('errorMessage').innerHTML = '<div class="alert alert-danger">'+isFromLegal+'</div>';
    }
}

function updateEmail()
{
    resetStyle('updateEmailForm');
    var isFromLegal = checkFrom('updateEmailForm');
    if(isFromLegal == 'YES')
    {
        sendForm('/api/updateUser', 'put', getFormData('updateEmailForm'), 'Update Email', '/myEmail');
    }
    else
    {
        document.getElementById('errorMessage').innerHTML = '<div class="alert alert-danger">'+isFromLegal+'</div>';
    }
}
function updatePassword()
{
    resetStyle('updatePasswordForm');
    var isFromLegal = checkFrom('updatePasswordForm');
    if(isFromLegal == 'YES')
    {
        let password = document.getElementById("password").value;
        let oldPassword = document.getElementById("oldPassword").value;
        //sendForm('/api/resetPassword', 'put', getFormData('updatePasswordForm'), 'Reset Password', '/resetPassword');
        $.ajax
        ({
            type : 'PUT',
            url : '/api/resetPassword',
            data: "password="+password+"&oldPassword="+oldPassword,
            success: function(result)
            {
                if(result == 'success')
                {
                    alertMessage('Reset Password Successfully', "/resetPassword");
                }
                else
                {
                    alertMessage('Reset Password Failed<br>Error: '+ result, "reload");
                }
            },
            error: function(jqXHR, exception)
            {
                if (jqXHR.status === 0)
                {alertMessage("Reset Password Failed<br>Not connect. Verify Network.", "reload");}
                else if (jqXHR.status == 404)
                {alertMessage("Reset Password Failed<br>Requested page not found. [404]", "reload");}
                else if (jqXHR.status == 500)
                {alertMessage("Reset Password Failed<br>Internal Server Error [500].", "reload");}
                else if (exception === "parsererror")
                {alertMessage("Reset Password Failed<br>Requested JSON parse failed.", "reload");}
                else if (exception === "timeout")
                {alertMessage("Reset Password Failed<br>Time out error.", "reload");}
                else if (exception === "abort")
                {alertMessage("Reset Password Failed<br>Ajax request aborted.", "reload");}
                else
                {alertMessage("Reset Password Failed<br>Uncaught Error.\n" + jqXHR.responseText, "reload");}
            }
        });
    }
    else
    {
        document.getElementById('errorMessage').innerHTML = '<div class="alert alert-danger">'+isFromLegal+'</div>';
    }
}
function updatePhone()
{
    resetStyle('updatePhoneForm');
    var isFromLegal = checkFrom('updatePhoneForm');
    if(isFromLegal == 'YES')
    {
        sendForm('/api/updateUser', 'put', getFormData('updatePhoneForm'), 'Update User Phone', '/myPhone');
    }
    else
    {
        document.getElementById('errorMessage').innerHTML = '<div class="alert alert-danger">'+isFromLegal+'</div>';
    }
}

function adminUpdateEmail(id)
{
    var isFromLegal = checkFrom('adminUpdateEmailForm');
    if(isFromLegal == 'YES')
    {
        sendForm('/api/admin/updateUser', 'put', getFormData('adminUpdateEmailForm'), 'Update User Email', '/userList/'+id);
    }
    else
    {
        document.getElementById('errorMessage').innerHTML = '<div class="alert alert-danger">'+isFromLegal+'</div>';
    }
}
function adminUpdatePassword(id)
{
    var isFromLegal = checkFrom('adminUpdatePasswordForm');
    if(isFromLegal == 'YES')
    {
        sendForm('/api/admin/updateUser', 'put', getFormData('adminUpdatePasswordForm'), 'Update User Password', '/userList/'+id);
    }
    else
    {
        document.getElementById('errorMessage').innerHTML = '<div class="alert alert-danger">'+isFromLegal+'</div>';
    }
}
function adminUpdatePhone(id)
{
    var isFromLegal = checkFrom('adminUpdatePhoneForm');
    if(isFromLegal == 'YES')
    {
        sendForm('/api/admin/updateUser', 'put', getFormData('adminUpdatePhoneForm'), 'Update Phone', '/userList/'+id);
    }
    else
    {
        document.getElementById('errorMessage').innerHTML = '<div class="alert alert-danger">'+isFromLegal+'</div>';
    }
}

function adminDeleteUser()
{
    sendForm('/api/admin/deleteUser', 'DELETE', getFormData('adminUpdateEmailForm'), 'Delete User', '/userList');
}

/* Blog Operation */
function postBlog()
{
    resetStyle('postBlogForm');
    document.getElementById("blog").style.height = "250px";
    var isFromLegal = checkFrom('postBlogForm');
    if(isFromLegal == 'YES')
    {
        sendForm('/api/postBlog', 'POST', getFormData('postBlogForm'), 'Post Blog', '/home');
    }
    else
    {
        document.getElementById('errorMessage').innerHTML = '<div class="alert alert-danger">'+isFromLegal+'</div>';
    }
}

function updateBlog()
{
    resetStyle('postBlogForm');
    document.getElementById("blog").style.height = "250px";
    var isFromLegal = checkFrom('postBlogForm');
    if(isFromLegal == 'YES')
    {
        sendForm('/api/updateBlog', 'PUT', getFormData('postBlogForm'), 'Update Blog', '/myBlog');
    }
    else
    {
        document.getElementById('errorMessage').innerHTML = '<div class="alert alert-danger">'+isFromLegal+'</div>';
    }
}

function deleteBlog()
{
    sendForm('/api/deleteBlog', 'DELETE', getFormData('postBlogForm'), 'Delete Blog', '/myBlog');
}

function adminDeleteBlog()
{
    sendForm('/api/admin/deleteBlog', 'DELETE', getFormData('userBlogForm'), 'Delete Blog', '/userBlog');
}

function thumbUp(id)
{
    $.ajax
    ({
        type : 'GET',
        url : '/api/thumbUp/' + id,
        success: function(result)
        {
            document.getElementById("thumbUp").innerHTML = '&#20;' + result;
        }})
}

function thumbDown(id)
{
    $.ajax
    ({
        type : 'GET',
        url : '/api/thumbDown/' + id,
        success: function(result)
        {
            document.getElementById("thumbDown").innerHTML = '&#20;' + result;
        }})
}

/* alert Pop windows */
function alertMessage(responseMessage, jumpPage)
{
    $('#alertMessageString').html(responseMessage);
    $('#alertMessage').show();
    setTimeout(function ()
    {
        $('#alertMessageString').html('');
        $('#alertMessage').hide();
        if(jumpPage == "reload")
        {
            location.reload();
        }
        else
        {
            window.location.href = jumpPage;
        }
    }, 2500);
}

/* Form check Methods */
// check password and confirm is same or not
function onChange()
{
    const password = document.getElementById('password');
    const confirm = document.getElementById('confirm');
    if(password != null && confirm != null)
    {
        console.log("password is not null");
        if (confirm.value === password.value)
        {
            console.log("password same");
            return "YES";
        }
        return "PassWord and Confirm Password not Match";
    }
    return "YES";
}

// reset FormStyle
// remove red broad.
function resetStyle(formId)
{
    document.getElementById('errorMessage').innerHTML = '';
    var formElements = document.getElementById(formId).elements;
    for( var i=0; i<formElements.length; i++ )
    {
        if(formElements[i].getAttribute('required') != null)
        {
            formElements[i].removeAttribute('style');
        }
    }
}

// check require input value is null or not
function checkFrom(formId)
{
    var formElements = document.getElementById(formId).elements;
    for( var i=0; i<formElements.length; i++ )
    {
        if(formElements[i].getAttribute('required') != null)
        {
            if((formElements[i].value == null)||(formElements[i].value == undefined)||(formElements[i].value == ''))
            {
                formElements[i].style.borderColor = "red";
                return formElements[i].name+' is required';
            }
        }
    }
    return onChange();
}

// clear all value in the form
function resetFrom(formId)
{
    document.getElementById('errorMessage').innerHTML = '';
    var formElements = document.getElementById(formId).elements;
    for( var i=0; i<formElements.length; i++ )
    {
        formElements[i].value = '';
        if(formElements[i].getAttribute('required') != null)
        {
            formElements[i].removeAttribute('style');
        }
    }
    return 'YES';
}


// get form data
function getFormData(formId)
{
    var form = document.getElementById(formId);
    var formData = new FormData(form);
    var object = {};
    var array = new Array();
    formData.forEach(function(value, key)
    {
        if((key == 'requirements'))
        {
            var src = value.replace(/(^\s*)|(\s*$)/g, '');
            if((src!=null)&&(src!=undefined)&&(src!=''))
            {
                array.push(value);
                object[key] = array;
            }
        }
        else
        {
            object[key] = value;
        }
    });
    return JSON.stringify(object);
}


// ajax send form
function sendForm(URL, requestType, data, operation, jumpPage)
{
    $.ajax
    ({
        type : requestType,
        contentType : 'application/json; charset=utf-8',
        datatype : 'text',
        url : URL,
        data: data,
        success: function(result)
        {
            if(result == 'success')
            {
                alertMessage(operation + ' Successfully', jumpPage);
            }
            else
            {
                alertMessage(operation + '\n Error: '+ result, "reload");
            }
        },
        error: function(jqXHR, exception)
        {
            if (jqXHR.status === 0)
            {alertMessage(operation + "<br>Not connect. Verify Network.", "reload");}
            else if (jqXHR.status == 404)
            {alertMessage(operation + "<br>Requested page not found. [404]", "reload");}
            else if (jqXHR.status == 500)
            {alertMessage(operation + "<br>Internal Server Error [500].", "reload");}
            else if (exception === "parsererror")
            {alertMessage(operation + "<br>Requested JSON parse failed.", "reload");}
            else if (exception === "timeout")
            {alertMessage(operation + "<br>Time out error.", "reload");}
            else if (exception === "abort")
            {alertMessage(operation + "<br>Ajax request aborted.", "reload");}
            else
            {alertMessage(operation + "<br>Uncaught Error.\n" + jqXHR.responseText, "reload");}
        }
    });
}


function showReplyView(blog_id, reply_id, title)
{
    let e = document.getElementById("replyTitle")
    e.innerHTML="Re: " + title;
    document.getElementById("blog_id").value = blog_id;
    document.getElementById("reply_id").value = reply_id;
    $('#replyView').modal('show');
}

function reply()
{
    document.getElementById("text").removeAttribute('style');
    document.getElementById("text").style.height = "120px";
    let blog_id = document.getElementById("blog_id").value
    var isFromLegal = checkFrom('replyForm');
    if(isFromLegal == 'YES')
    {
        sendForm('/api/postReply', 'POST', getFormData('replyForm'), 'Post Reply', '/blog/' +blog_id);
    }
}



