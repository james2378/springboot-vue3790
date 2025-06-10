<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','hotel_number') || $check_field('add','hotel_number') || $check_field('set','hotel_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="酒店编号" prop="hotel_number">
					<el-input id="hotel_number" v-model="form['hotel_number']" placeholder="请输入酒店编号"
							  v-if="user_group === '管理员' || (form['hotel_management_id'] && $check_field('set','hotel_number')) || (!form['hotel_management_id'] && $check_field('add','hotel_number'))" :disabled="disabledObj['hotel_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','hotel_number')">{{form['hotel_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','hotel_name') || $check_field('add','hotel_name') || $check_field('set','hotel_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="酒店名称" prop="hotel_name">
					<el-input id="hotel_name" v-model="form['hotel_name']" placeholder="请输入酒店名称"
							  v-if="user_group === '管理员' || (form['hotel_management_id'] && $check_field('set','hotel_name')) || (!form['hotel_management_id'] && $check_field('add','hotel_name'))" :disabled="disabledObj['hotel_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','hotel_name')">{{form['hotel_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','layout_of_a_house_or_an_apartment') || $check_field('add','layout_of_a_house_or_an_apartment') || $check_field('set','layout_of_a_house_or_an_apartment')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="房型" prop="layout_of_a_house_or_an_apartment">
					<el-select id="layout_of_a_house_or_an_apartment" v-model="form['layout_of_a_house_or_an_apartment']"
						v-if="user_group === '管理员' || (form['hotel_management_id'] && $check_field('set','layout_of_a_house_or_an_apartment')) || (!form['hotel_management_id'] && $check_field('add','layout_of_a_house_or_an_apartment'))">
						<el-option v-for="o in list_layout_of_a_house_or_an_apartment" :key="o['layout_of_a_house_or_an_apartment']" :label="o['layout_of_a_house_or_an_apartment']"
							:value="o['layout_of_a_house_or_an_apartment']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','layout_of_a_house_or_an_apartment')">{{form['layout_of_a_house_or_an_apartment']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','price') || $check_field('add','price') || $check_field('set','price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="价格" prop="price">
					<el-input-number id="price" v-model.number="form['price']"
						v-if="user_group === '管理员' || (form['hotel_management_id'] && $check_field('set','price')) || (!form['hotel_management_id'] && $check_field('add','price'))"></el-input-number>
					<div v-else-if="$check_field('get','price')">{{form['price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','photo') || $check_field('add','photo') || $check_field('set','photo')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="照片" prop="photo">
					<el-upload :disabled="disabledObj['photo_isDisabled']" id="photo" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_photo"
						:show-file-list="false" v-if="user_group === '管理员' || (form['hotel_management_id'] && $check_field('set','photo')) || (!form['hotel_management_id'] && $check_field('add','photo'))">
						<img v-if="form['photo']" :src="$fullUrl(form['photo'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','photo')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['photo'])" :preview-src-list="[$fullUrl(form['photo'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','address') || $check_field('add','address') || $check_field('set','address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="地址" prop="address">
					<el-input id="address" v-model="form['address']" placeholder="请输入地址"
							  v-if="user_group === '管理员' || (form['hotel_management_id'] && $check_field('set','address')) || (!form['hotel_management_id'] && $check_field('add','address'))" :disabled="disabledObj['address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','address')">{{form['address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','amenities') || $check_field('add','amenities') || $check_field('set','amenities')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="便利设施" prop="amenities">
					<el-input type="textarea" id="amenities" v-model="form['amenities']" placeholder="请输入便利设施"
						v-if="user_group === '管理员' || (form['hotel_management_id'] && $check_field('set','amenities')) || (!form['hotel_management_id'] && $check_field('add','amenities'))" :disabled="disabledObj['amenities_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','amenities')">{{form['amenities']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','traffic_guide') || $check_field('add','traffic_guide') || $check_field('set','traffic_guide')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="交通指南" prop="traffic_guide">
					<el-input type="textarea" id="traffic_guide" v-model="form['traffic_guide']" placeholder="请输入交通指南"
						v-if="user_group === '管理员' || (form['hotel_management_id'] && $check_field('set','traffic_guide')) || (!form['hotel_management_id'] && $check_field('add','traffic_guide'))" :disabled="disabledObj['traffic_guide_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','traffic_guide')">{{form['traffic_guide']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','hotel_introduction') || $check_field('add','hotel_introduction') || $check_field('set','hotel_introduction')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="酒店介绍" prop="hotel_introduction">
					<quill-editor v-model.number="form['hotel_introduction']"
						v-if="user_group === '管理员' || (form['hotel_management_id'] && $check_field('set','hotel_introduction')) || (!form['hotel_management_id'] && $check_field('add','hotel_introduction')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','hotel_introduction')" v-html="form['hotel_introduction']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "hotel_management_id",
				url_add: "~/api/hotel_management/add?",
				url_set: "~/api/hotel_management/set?",
				url_get_obj: "~/api/hotel_management/get_obj?",
				url_upload: "~/api/hotel_management/upload?",

				query: {
					"hotel_management_id": 0,
				},

				form: {
					"hotel_number":'', // 酒店编号
					"hotel_name":'', // 酒店名称
					"layout_of_a_house_or_an_apartment":'', // 房型
					"price":0, // 价格
					"photo":'', // 照片
					"address":'', // 地址
					"amenities":'', // 便利设施
					"traffic_guide":'', // 交通指南
					"hotel_introduction":'', // 酒店介绍
					"hotel_management_id": 0, // ID

				},
				disabledObj:{
					"hotel_number_isDisabled": false,
					"hotel_name_isDisabled": false,
					"layout_of_a_house_or_an_apartment_isDisabled": false,
					"photo_isDisabled": false,
					"address_isDisabled": false,
					"amenities_isDisabled": false,
					"traffic_guide_isDisabled": false,
					"hotel_introduction_isDisabled": false,
				},
				//房型选项列表
				list_layout_of_a_house_or_an_apartment: [],

			}
		},
		methods: {
			/**
			 * 获取房型列表
			 */
			async get_list_layout_of_a_house_or_an_apartment() {
				var json = await this.$get("~/api/room_type_management/get_list?");
				if(json.result && json.result.list){
					this.list_layout_of_a_house_or_an_apartment = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传照片
			 * @param {Object} param图片参数
			 */
			upload_photo(param){
				this.uploadFile(param.file, "photo");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/hotel_management/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/hotel_management/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/hotel_management/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/hotel_management/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/hotel_management/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_layout_of_a_house_or_an_apartment();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
