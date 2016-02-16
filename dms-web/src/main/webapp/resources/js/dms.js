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
           			"productId" : 1,
           			"docConfId" : 9,
           			"docTypeCode" : "LEFTIMAGE",
           			"isMandatory" : false,
           			"isMultipleItemAllowed" : false,
           			"groupId" : null,
           			"description" : null		
           		},
           		{
           			"productId" : 1,
           			"docConfId" : 10,
           			"docTypeCode" : "RIGHTIMAGE",
           			"isMandatory" : false,
           			"isMultipleItemAllowed" : false,
           			"groupId" : null,
           			"description" : null		
           		},
           		{
           			"productId" : 1,
           			"docConfId" : 11,
           			"docTypeCode" : "BACKIMAGE",
           			"isMandatory" : false,
           			"isMultipleItemAllowed" : false,
           			"groupId" : null,
           			"description" : null		
           		}

           ]


var ImgContainer = React.createClass({
	
	
	render: function(){
	// rendering DOM updates
	console.log(this.props.confData);
	
	var confData = this.props.confData;
	
	return 	(
			<div id="imageRow" className="row">
				{
					confData.map(function(conf){
						return (
								<form action="https://devdmsprodicts01.s3.amazonaws.com/dev/" method="post" encType="multipart-formdata">
									<div id="imgContainer" className="col-xs-6 col-md-3">
										<a href="#" id="imgThumbnail" className="thumbnail">
											<img src="http://www.kerry-beaches.com/images/skellig-michael-puffin-small.jpg" />
										</a>
										<p >
											<input type="hidden" name="$key" value="test_{conf.docTypeCode}" />
											<input type="hidden" name="acl" value="public-read-write" />
											<input type="file" name="imgFile" value="" id="imagefile" />
											<button type="button" className="btn btn-danger">Remove</button>
										</p>       
									</div>
									
									
								</form>
						);
					})
				}
			</div>
	)
				
	}
	
});


ReactDOM.render(
	<ImgContainer confData={productConf}/>,
	document.getElementById('dmscontainer')
);
	


