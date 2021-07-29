let myLeads = []
const inputBtn = document.getElementById("input-btn")
const myElement = document.getElementById("in-txt")
const ulEl = document.getElementById("ul-el")
const deleteBtn = document.getElementById("delete-btn")
const savetabBtn = document.getElementById("savetab-btn")


savetabBtn.addEventListener("click", function(){
    chrome.tabs.query({active:true, currentWindow: true}, function(tabs) {
    myLeads.push(tabs[0].url)
    localStorage.setItem("myLeads",JSON.stringify(myLeads))
    renderLeads()
    })
})

inputBtn.addEventListener("click", function(){
    myLeads.push(myElement.value)
    //converintg myLeads to string and saving to local storage bcz LclSt stores only strings
    localStorage.setItem("myLeads",JSON.stringify(myLeads))
    renderLeads()
    myElement.value = ''
})

//converting lcl stored string to array using JSON parse
let leadsFromlocalStorage = JSON.parse(localStorage.getItem("myLeads"))
if(leadsFromlocalStorage){
    //assigning localstorage array to myLeads
    myLeads = leadsFromlocalStorage
    renderLeads()
}

function renderLeads(){
    let listItems = ""
    for(let i =0; i < myLeads.length; i++){
        listItems += "<li><a  target=_blank href='" + myLeads[i] + "'>"+ myLeads[i] +"</li>"
    }
    ulEl.innerHTML = listItems
}

//prforming delete button
deleteBtn.addEventListener("dblclick", function(){
    localStorage.clear()
    myLeads = []
    renderLeads()
})