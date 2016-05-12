/**
 * React.js code for dms page
 */

var productConf = [
           		{
           			"productId" : 1,
           			"docConfId" : 8,
           			"docTypeCode" : "FRONTIMAGE",
           			"isMandatory" : false,
           			"isMultipleItemAllowed" : false,
           			"groupId" : null,
           			"description" : null		
           		},
           		{
           			"productId" : 2,
           			"docConfId" : 9,
           			"docTypeCode" : "LEFTIMAGE",
           			"isMandatory" : false,
           			"isMultipleItemAllowed" : false,
           			"groupId" : null,
           			"description" : null		
           		},
           		{
           			"productId" : 3,
           			"docConfId" : 10,
           			"docTypeCode" : "RIGHTIMAGE",
           			"isMandatory" : false,
           			"isMultipleItemAllowed" : false,
           			"groupId" : null,
           			"description" : null		
           		},
           		{
           			"productId" : 4,
           			"docConfId" : 11,
           			"docTypeCode" : "BACKIMAGE",
           			"isMandatory" : false,
           			"isMultipleItemAllowed" : false,
           			"groupId" : null,
           			"description" : null		
           		}

           ]

var MyDocForm = React.createClass({
	
	propTypes :{
		docUploadURL : React.PropTypes.string,
		defaultImageUrl : React.PropTypes.string
	},
	
	
	getDefaultProps: function(){
		return {
			defaultImageUrl : 'https://s3-us-west-2.amazonaws.com/devdmsproducts01/global_default/img_not_available.jpg',
			urlToPost : 'http://devdmsproducts01.s3.amazonaws.com/',
			apiRoot: 'http://localhost:8080/dms-web/api/'
		};
	},
	
	getInitialState: function() {
	    return {
	    	awskey: "test_"+this.props.confData.docTypeCode,
	    	awsacl: "public-read-write",
	    	imgSrc: this.props.confData.docUrl ? this.props.confData.docDetails.docUrl :  this.props.defaultImageUrl,
	    	documentToUpload: ''
	    	};
	  },
	  
	  uploadProductDetail: function(pDoc){
		  
		  console.log("uploading product data " + this.props.apiRoot+"details/product/");
		  console.log("uploaded image path : " + pDoc.docUrl);
		  console.log("product Document details  : " + JSON.stringify(pDoc));
		  
		  $.ajax({
				url: this.props.apiRoot+"details/product/doc",
				type: 'POST',
				data: JSON.stringify(pDoc),
				processData: false,
				contentType: "application/json",
			    dataType: "json",
			 success: function(data){
					console.log("product data Successfully  added");
				}.bind(this),
				error: function(xhr, status, err){
					console.log("Error while posting product details");
					console.log(urlToPost, status, err);
				}.bind(this)
			});
	  },
	  
	docFileChange: function(e){
		console.log(e);
		this.setState({
			documentToUpload : e.target.value
		})
		var reader = new FileReader();
		var imgFile = this.refs.file.files[0];
		console.log(" Image to be uploaded : " + imgFile.name);
		var imgCmponent =  this.refs.thmbImg ;
		reader.onload = function(event) {
			imgCmponent.src = event.target.result;
			//console.log(event.target.result);
		}
		reader.readAsDataURL(imgFile);
	} , 
	handleForm: function(e){
		// make ajax call
		 e.preventDefault();
		
		console.log(e);
		console.log("Form State ==>" + this.state);
		
		var awsData = {
				key: "dev/"+this.refs.file.files[0].name,
				acl: this.state.awsacl,
		}
		
		console.log ("file to be uploaded : " + this.refs.file.files[0] );
		console.log ("AWS Key " + awsData.key );
		console.log ("AWS acl " + awsData.acl );
		var fd = new FormData();

		// fd.append( 'file', this.refs.file.files[0] );
		fd.append( 'acl', awsData.acl);
		fd.append( 'key', awsData.key);
		
		var files = this.refs.file.files;
		var file = files[0];
		
		console.log("File to Uploaded : " + file.name);
		console.log("File size : " + file.size);

		fd.append('file', file, file.name);
		console.log(fd.get(file.name));
	
		//data for second ajax call to persist information in DB
		console.log("Product document to be added" + this.props.confData.productId)
		var productDocument = {
				productDocDetailId: null,
			    productId: this.props.confData.docConf.productId,
				productDocConfId: this.props.confData.docConf.docConfId,
				docUrl: this.props.urlToPost+awsData.key
		}
		
		alert("Image source :" + productDocument.docUrl);
		alert("product ID:" + productDocument.productId);
		
		alert("Image Upload URL : " + this.props.urlToPost);
		// Ajax call
		
		//var urlToPost = 'http://devdmsproducts01.s3.amazonaws.com/';
		$.ajax({
			url: this.props.urlToPost,
			type: 'POST',
			data: fd,
			processData: false,
            contentType: false,
            // file: file,
			success: function(data){
				this.uploadProductDetail(productDocument);
			}.bind(this),
			error: function(xhr, status, err){
				console.log("Error while uploading the file");
				console.log(urlToPost, status, err);
			}.bind(this)
		});
	},	
	render: function(){
		alert("MyDocForm Doctype code while renedering " + this.props.confData.docConf.docTypeCode);
		console.log("MyDocForm Doctype code while renedering " + JSON.stringify(this.props.confData.docConf));
		return (
				<form ref="uploadForm" method="POST" encType="multipart/form-data" onSubmit={this.handleForm} >
				<div id="imgContainer" className="col-xs-6 col-md-3">
					<a href="#" id="imgThumbnail" className="thumbnail">
						<img ref="thmbImg" src={this.state.imgSrc} />
					</a>
					<p>
						<input  type="hidden" name="key" value={this.state.awskey} />
						<input type="hidden" name="acl" value={this.state.awsacl} />
						<input ref="file" type="file" name="file" onChange={this.docFileChange} value={this.state.documentToUpload} id="imagefile" /> 
						<input type="submit" name="submit" value="Upload to Amazon S3" />		
						<button type="button" className="btn btn-danger">Remove</button>
					</p>       
				</div>
			</form>
		);
		
	}
});


var DocContainer = React.createClass({
	
	
	getDefaultProps: function(){
		return {
			apiRoot: 'http://localhost:8080/dms-web/api/'
		};
	},
	getInitialState: function() {
		console.log(" Initialized the doc container ");
		
	    return {
	    		data: []
	    	};
	  },
	componentWillMount: function() {
		
		console.log(" componentWillMount - Component will mount here : " + JSON.stringify(this.state.data));
		var productId = this.props.productId;
		alert(" Product Id : " + productId);
		var urlToPost = this.props.apiRoot+"details/product/"+productId;
	    alert ("URL To POST : " + urlToPost);
	    
	    //Ajax call to get product details	    
	    	$.ajax({
	    	      url: urlToPost,
	    	      dataType: 'json',
	    	      cache: false,
	    	      success: function(data) {
	    	    	console.log("componentWillMount - Product Data Retrieved " +  data); 
	    	        this.setState({data: data});
	    	      }.bind(this),
	    	      error: function(xhr, status, err) {
	    	        console.error(this.props.url, status, err.toString());
	    	      }.bind(this)
	    	    });
	  },
	  componentDidMount: function() {
			
			console.log(" componentDidMount - Component did mount here : " + JSON.stringify(this.state.data));
			var productId = this.props.productId;
			alert(" Product Id : " + productId);
			var urlToPost = this.props.apiRoot+"details/product/"+productId;
		    alert ("URL To POST : " + urlToPost);
		    
		    //Ajax call to get product details	    
		    	$.ajax({
		    	      url: urlToPost,
		    	      dataType: 'json',
		    	      cache: false,
		    	      success: function(data) {
		    	    	console.log("componentWillMount - Product Data Retrieved " +  data); 
		    	        this.setState({data: data});
		    	      }.bind(this),
		    	      error: function(xhr, status, err) {
		    	        console.error(this.props.url, status, err.toString());
		    	      }.bind(this)
		    	    });
		  },
	render: function(){
	// rendering DOM updates
		var formElements = [];
		var confData = this.state.data;
		console.log(" DocContainer Conf Data while rendering in parent  : " +   confData);
		confData.forEach(function(conf){
			console.log("DocContainer each Conf Data : " +   JSON.stringify(conf));
			formElements.push(<MyDocForm confData={conf} key={conf.docConfId} />);
		});
		
		return(
					<div id="imageRow" className="row">
						{formElements}
					</div>
			  );
		}
	
});

ReactDOM.render(
	//var productId = document.getElementById('productId'),
	<DocContainer productId={10}/>,
	document.getElementById('dmscontainer')
);
	


