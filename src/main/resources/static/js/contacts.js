console.log("contact.js is running")

const BaseUrl="http://localhost:8081"

const viewcontactModel  = document.getElementById('view-contact-modal');

// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
id: 'view-contact-modal',
  override: true
};


const modal = new Modal(viewcontactModel, options, instanceOptions);

function openContactModal(){
    modal.show();
}

function closeContactModal(){
    modal.hide();
}


async function loadContactData(id){  
    console.log("id is = =",id);
   try {

    const data = await (
        await   fetch(`${BaseUrl}/api/contacts/${id}`)
        ).json();
        
        console.log(data);
        console.log(data.name);

        // adding the data to modal

        document.querySelector("#contact_name").innerHTML=data.name;
        document.querySelector("#contact_email").innerHTML=data.email;
        document.querySelector("#contact_phoneNumber").innerHTML=data.phoneNumber;
        document.querySelector("#contact_address").innerHTML=data.address;
        document.querySelector("#contact_picture").src=data.picture;
        document.querySelector("#contact_discription").innerHTML=data.discription;
        const contactFavorite = document.querySelector("#contact_favourite");
        if (data.favourite) {
         contactFavorite.innerHTML =
        "<i class='fa-solid fa-heart text-pink-400'></i><i class='fa-solid fa-heart text-pink-400'></i><i class='fa-solid fa-heart text-pink-400'></i>";
         } else {
        contactFavorite.innerHTML = "Not Favorite Contact";
        }

        openContactModal();

    
   } catch (error) {
      console.log("Error:",error)
   }
}



// delete the contact

async function deleteContact(id){

    Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire({
            title: "Deleted!",
            text: "Your file has been deleted.",
            icon: "success"
            
            
          });
          const url = `${BaseUrl}/user/contacts/delete/${id}`
          window.location.replace(url);
        }
      });

}