
import React from 'react'

import resets from '../_resets.module.css';
import ItemText from './ItemText/ItemText';
import classes from './QLDonHang.module.css';
import Row  from './Row/Row';

/* @figmaId 14:553 */
const QLDonHang = (props) =>{
  return (
    
    <div className={`${resets.storybrainResets} ${classes.root}`}>
      <div className={classes.rectangle21}></div>
      <div className={classes.menu_FILL0_wght400_GRAD0_opsz4}></div>
      <div className={classes.hETHONGQUANLYCUAHANGTHECOFFEEH}>HỆ THỐNG QUẢN LÝ CỬA HÀNG THE COFFEE HOUSE</div>
      <div className={classes.qUANLYONHANG}>QUẢN LÝ ĐƠN HÀNG</div>
      <div className={classes.frame1}>
        <Row
          className={classes.row}
          classes={{ itemText: classes.itemText }}
          swap={{
            itemButton1: (
              <ItemText
                className={classes.itemText2}
                text={{
                  tEXT: <div className={classes.tEXT5}>Action</div>,
                }}
              />
            ),
          }}
          text={{
            tEXT: <div className={classes.tEXT}>ID Hóa Đơn</div>,
            tEXT2: <div className={classes.tEXT2}>ID Nhân Viên</div>,
            tEXT3: <div className={classes.tEXT3}>ID Khách Hàng</div>,
            tEXT4: <div className={classes.tEXT4}>Ngày Lập</div>,
          }}
        />
        <div className={classes.rectangle17}></div>
        <Row
          className={classes.row2}
          text={{
            tEXT: <div className={classes.tEXT6}>HD01</div>,
            tEXT2: <div className={classes.tEXT7}>NV01</div>,
            tEXT3: <div className={classes.tEXT8}>KH01</div>,
            tEXT4: <div className={classes.tEXT9}>23/02/2023</div>,
          }}
        />
        <div className={classes.rectangle18}></div>
        <Row
          className={classes.row3}
          text={{
            tEXT: <div className={classes.tEXT10}>HD02</div>,
            tEXT2: <div className={classes.tEXT11}>NV02</div>,
            tEXT3: <div className={classes.tEXT12}>KH02</div>,
            tEXT4: <div className={classes.tEXT13}>13/01/2023</div>,
          }}
        />
        <div className={classes.rectangle19}></div>
        <Row
          className={classes.row4}
          text={{
            tEXT: <div className={classes.tEXT14}>HD03</div>,
            tEXT2: <div className={classes.tEXT15}>NV03</div>,
            tEXT3: <div className={classes.tEXT16}>KH03</div>,
            tEXT4: <div className={classes.tEXT17}>14/02/2023</div>,
          }}
        />
        <div className={classes.rectangle20}></div>
        <Row
          className={classes.row5}
          text={{
            tEXT: <div className={classes.tEXT18}>HD04</div>,
            tEXT2: <div className={classes.tEXT19}>NV04</div>,
            tEXT3: <div className={classes.tEXT20}>KH04</div>,
            tEXT4: <div className={classes.tEXT21}>15/02/2023</div>,
          }}
        />
      </div>
      <div className={classes.layers_FILL0_wght400_GRAD0_ops}></div>
      <div className={classes.quanLySanPham}>Quản lý sản phẩm</div>
      <div className={classes.layers_FILL0_wght400_GRAD0_ops2}></div>
      <div className={classes.quanLyOnHang}>Quản lý đơn hàng</div>
      <div className={classes.layers_FILL0_wght400_GRAD0_ops3}></div>
      <div className={classes.layers_FILL0_wght400_GRAD0_ops4}></div>
      <div className={classes.layers_FILL0_wght400_GRAD0_ops5}></div>
      <div className={classes.quanLyKhachHang}>Quản lý khách hàng</div>
      <div className={classes.quanLyNhanVien}>Quản lý nhân viên</div>
      <div className={classes.quanLyTaiKhoan}>Quản lý tài khoản</div>
      <div className={classes.dANHMUCQUANLY}>DANH MỤC QUẢN LÝ</div>
      <div className={classes.ANGNHAPHETHONG}>ĐĂNG NHẬP HỆ THỐNG</div>
      <button className={classes.taiKhoan} onClick={props.logout}>Tài khoản</button>
      <div className={classes.account_circle_FILL0_wght400_G}></div>
      <div className={classes.logout_FILL0_wght400_GRAD0_ops}></div>
      <div className={classes.OiMatKhau}>Đổi mật khẩu</div>
      <div className={classes.key_FILL0_wght400_GRAD0_opsz48}></div>
    </div>
  );
};

export default QLDonHang;