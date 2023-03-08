const status = {
    setData(x, y) {
        if (x != null && y != null) {
            x = x.toString()
            y = y.toString()
            localStorage.setItem(x, y);
        }
    },
    removeData(x) {
        if (x != null) {
            localStorage.removeItem(x)
        }
    },
    getData(x) {
        if (x != null) {
            try{
            return  localStorage.getItem(x)
        }
        catch (e) {
            return ""
        }
        }
    },
    removeUser(name) {
        if (!name.isEmpty) {
            try {
                localStorage.removeItem(name)
                return true;
            } catch (e) {
                return false;
            }
        }
    },
    getUser(name) {
        if (!name.isEmpty) {
            try {
                return localStorage.getItem(name)
            } catch (e) {
                return ""
            }
        }
    }
}
export default status
