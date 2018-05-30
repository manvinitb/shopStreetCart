package com.shopstreet.backend.cart.restclient;

import com.shopstreet.backend.cart.restclient.dto.MailClientDTO;
import com.shopstreet.backend.cart.restclient.dto.MailClientItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Component
public class MailClient {
    static StringBuilder builder = new StringBuilder();
    static String emailMessage;

    static {
        builder.append("Hi Amigo!\n\n");
        builder.append("Your order with order id: %d has been successfully placed.\n");
        builder.append("Thanks for choosing shopStreet!!\n\n");
        builder.append("Cheers :)");
        emailMessage = builder.toString();
    }

    @Autowired
    private JavaMailSender sender;

    public void sendEmail(Long orderId, String emailId, MailClientDTO mailClientDTO) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(emailId);
        //helper.setText(String.format(emailMessage, orderId));

        /////////////////////
        List<MailClientItemDTO> list = mailClientDTO.getList();

        StringBuilder template = new StringBuilder();


        template.append("<html>\n" +
                "    <body>\n" +
                "<table width=\"100%\" bgcolor=\"#fcfcfc\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"left-image\">\n" +
                "    <tbody>\n" +
                "      <tr>\n" +
                "        <td>\n" +
                "          <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n" +
                "            <tbody>\n" +
                "              <tr>\n" +
                "                <td width=\"100%\">\n" +
                "                  <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n" +
                "                    <tbody>")
                .append("<tr>\n" +
                        "                                    <td style=\"font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #282828; text-align:center; line-height: 24px;\">\n" +
                        "                                      Hey Amigo!! Your Order With Order Id : ")
                .append(orderId.toString())
                .append(" Is Successfully Placed.\n" +
                        "\n" +
                        "                                    </td>\n" +
                        "                                  </tr>\n" +
                        "                                  <tr>\n" +
                        "                                        <td width=\"100%\" height=\"15\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">\n" +
                        "                                          &nbsp;\n" +
                        "                                        </td>\n" +
                        "                                      </tr>");

        for (int i = 0; i < list.size(); i++) {

            template.append("<tr>\n" +
                    "                        <td>\n" +
                    "                          <!-- Start of left column -->\n" +
                    "                          <table width=\"280\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidth\">\n" +
                    "                            <tbody>\n" +
                    "                              <!-- image -->\n" +
                    "                              <tr>\n" +
                    "                                <td width=\"280\" height=\"140\" align=\"center\" class=\"devicewidth\">\n" +
                    "                                  <img src=\"")
                    .append(list.get(i).getImage())
                    .append("\" alt=\"\" border=\"0\" width=\"280\" height=\"140\" style=\"display:block; border:none; outline:none; text-decoration:none;\" class=\"col2img\">\n" +
                            "                                </td>\n" +
                            "                              </tr>\n" +
                            "                              <!-- /image -->\n" +
                            "                            </tbody>\n" +
                            "                          </table>\n" +
                            "                          <!-- end of left column -->\n" +
                            "                          <!-- spacing for mobile devices-->\n" +
                            "                          <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"mobilespacing\">\n" +
                            "                            <tbody>\n" +
                            "                              <tr>\n" +
                            "                                <td width=\"100%\" height=\"15\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">\n" +
                            "                                  &nbsp;\n" +
                            "                                </td>\n" +
                            "                              </tr>\n" +
                            "                            </tbody>\n" +
                            "                          </table>\n" +
                            "                          <!-- end of for mobile devices-->\n" +
                            "                          <!-- start of right column -->\n" +
                            "                          <table width=\"280\" align=\"right\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidth\">\n" +
                            "                            <tbody>\n" +
                            "                              <tr>\n" +
                            "                                <td>\n" +
                            "                                  <table width=\"280\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidth\">\n" +
                            "                                    <tbody>\n" +
                            "                                      <!-- title -->\n" +
                            "                                      <tr>\n" +
                            "                                        <td style=\"font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #282828; text-align:left; line-height: 24px;\">")
                    .append(list.get(i).getProductName())
                    .append("                                        </td>\n" +
                            "                                      </tr>\n" +
                            "                                      <!-- end of title -->\n" +
                            "                                      <!-- Spacing -->\n" +
                            "                                      <tr>\n" +
                            "                                        <td width=\"100%\" height=\"15\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">\n" +
                            "                                          &nbsp;\n" +
                            "                                        </td>\n" +
                            "                                      </tr>\n" +
                            "                                      <!-- /Spacing -->\n" +
                            "                                      <!-- content -->\n" +
                            "                                      <tr>\n" +
                            "                                        <td style=\"font-family: Helvetica, arial, sans-serif; font-size: 14px; color: #889098; text-align:left; line-height: 24px;\">\n" +
                            "                                          " + list.get(i).getPrice()
                            + "\n" +
                            "                                        </td>\n" +
                            "                                      </tr>\n" +
                            "                                      <!-- end of content -->\n" +
                            "                                \n" +
                            "\n" +
                            "                                    </tbody>\n" +
                            "                                  </table>\n" +
                            "                                </td>\n" +
                            "                              </tr>\n" +
                            "                            </tbody>\n" +
                            "                          </table>\n" +
                            "                          <!-- end of right column -->\n" +
                            "                        </td>\n" +
                            "                        \n" +
                            "                      </tr>");


        }
        template.append("<tr>\n" +
                "                                <td width=\"100%\" height=\"15\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">\n" +
                "                                  &nbsp;\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                              <tr>\n" +
                "                                    <td style=\"font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #282828; text-align:center; line-height: 24px;\">\n" +
                "                                      Thanks For Choosing ShopStreet!!\n" +
                "\n" +
                "                                    </td>\n" +
                "                                  </tr>");
        template.append("</tbody>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "</body>\n" +
                "<html>");

        //////////////////////

        helper.setText(template.toString(), true);


        helper.setSubject("Order Confirmation!!");
        sender.send(message);
    }
}
