/**
 * React.js code for dms page
 */


/**
 * Local testing data 

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
 */
var MyDocForm = React.createClass({
	
	propTypes :{	
		docUploadURL : React.PropTypes.string,
		defaultImageUrl : React.PropTypes.string
	},
	
	
	getDefaultProps: function(){
		return {
			defaultImageUrl : 'https://s3-us-west-2.amazonaws.com/devdmsproducts01/global_default/img_not_available.jpg',
			urlToPost : 'http://devdmsproducts01.s3.amazonaws.com/'
		};
	},
	getInitialState: function() {
	    return {
	    	awskey: "test_"+this.props.confData.docTypeCode,
	    	awsacl: "public-read-write",
	    	imgSrc: this.props.confData.document ? this.props.confData.document.docUrl :  this.props.defaultImageUrl,
	    	documentToUpload: ''
	    	};
	  },
	  uploadProductDetail: function(pDoc){
		  console.log("Form : uploading product data " + this.props.apiRoot+"details/product/");
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
		var fd = new FormData();

		fd.append( 'acl', awsData.acl);
		fd.append( 'key', awsData.key);
		
		var files = this.refs.file.files;
		var file = files[0];
		
		console.log("File to Uploaded : " + file.name);
		//data for second ajax call to persist information in DB
		console.log("Product document to be added" + this.props.confData.productId)
		var productDocument = {
				productDocDetailId: null,
			    productId: this.props.confData.docConf.productId,
				productDocConfId: this.props.confData.docConf.docConfId,
				docUrl: this.props.urlToPost+awsData.key
		}
		// Ajax call
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
	
//	getDefaultProps: function(){
//		return {
//			apiRoot: 'http://localhost:8080/dms-web/api/'
//		};
//	},
	getInitialState: function() {
		console.log(" Initialized the doc container ");
	    return {
	    		data: []
	    	};
	  },
	componentWillMount: function() {
		
		var productId = this.props.productId;
		var urlToPost = this.props.apiRoot+"details/product/"+productId;
	    
	    //Ajax call to get product details	    
	    	$.ajax({
	    	      url: urlToPost,
	    	      dataType: 'json',
	    	      cache: false,
	    	      success: function(data) {
	    	    	console.log("DocContainer.componentWillMount - Product Data Retrieved " +  JSON.stringify(data)); 
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
		var apiRoot = this.props.apiRoot;
		console.log(" DocContainer Conf Data while rendering in parent  : " +   JSON.stringify(confData));
		console.log(" apiRoot : " +   apiRoot);
		confData.forEach(function(conf){
			console.log("Doc Container: Passing apiRoot to Form : " + apiRoot);
			formElements.push(<MyDocForm confData={conf} key={conf.docConfId} apiRoot={apiRoot} />);
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
	<DocContainer productId={10} apiRoot={'http://localhost:8080/dms-web/api/'}/>,
	document.getElementById('dmscontainer')
);
	


