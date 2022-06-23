import axios from 'axios';

const DataFetcher = (params) => {
    function fetchData(){
        var data;
        try{
            axios.get(params.url).then((resp) => {
                data = resp.data;
            })
        }catch(err){
            console.error(err);
        }
        return data;
    }

    return (
        {fetchData}
    )
}

export default DataFetcher;