import axios from 'axios'

const service = axios.create({
    baseURL: 'http://localhost:8080/',
    timeout: 60000,
    withCredentials: true,
    headers: {
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest',
    },
})

service.interceptors.request.use(
    function (config) {
        return config
    },
    function (error) {
        console.log(error)
        return Promise.reject(error)
    }
)

service.interceptors.response.use(
    function (response) {
        // console.log(response)
        const dataAxios = response.data
        // const code = dataAxios.reset
        return dataAxios
    },
    function (error) {
        // console.log(error)
        // alert(error)
        return Promise.reject(error)
    }
)

export default service
