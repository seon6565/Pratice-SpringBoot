async function getReplyList({bbs_idx,page,page_size,goLast}){
    const result = await axios.get(`/bbs/list`,{params:{page,page_size}});

    if(goLast=="true"){
        const total = result.data.total_count;
        const lastPage = parseInt(Math.ceil(total/page_size));
        return getReplyList({bbs_idx:bbs_idx,page:lastPage,page_size:page_size});

    }
    return result.data;
}