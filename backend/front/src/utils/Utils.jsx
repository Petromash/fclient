
class Utils {

    saveUser(data) {
        localStorage.setItem('user', JSON.stringify(data.user))
        localStorage.setItem('token', JSON.stringify(data.token))
    }

    removeUser() {
        localStorage.removeItem('user')
        localStorage.removeItem('token')
    }

    getToken()
    {
        return JSON.parse(localStorage.getItem('token'))
    }

    getUserName()
    {
        let user = JSON.parse(localStorage.getItem('user'))
        return user && user.login;
    }

    getUser()
    {
        return JSON.parse(localStorage.getItem('user'))
    }
}

export default new Utils()